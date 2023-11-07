package com.ming.controller;

import com.ming.Application;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.constants.BpmnXMLConstants;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FlowableControllerTest {
    @Resource
    RuntimeService runtimeService;

    @Resource
    TaskService taskService;

    @Resource
    HistoryService historyService;

    @Resource
    RepositoryService repositoryService;

    private String proc_key = "T_1103";

    private String zhangsan = "zhangsan";
    private String lisi = "lisi";
    private String wangwu = "wangwu";


    // 开启一个流程
    @Test
    public void startProcess() {
        HashMap<String, Object> map = new HashMap<>();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(proc_key, map);
        runtimeService.setVariable(processInstance.getId(), "test", "测试变量");
        log.info("创建复杂流程 processId：{}", processInstance.getId());

        // 使用流程实例id获取任务id
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).active().singleResult();

        // 设置张三为审批人
        taskService.addCandidateUser(task.getId(), zhangsan);
    }

    // 查询张三任务、张三处理通过之后，将候选人设置为李四
    @Test
    public void zhangSanTask() {
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(zhangsan).orderByTaskId().desc().list();
        //Task tasks = taskService.createTaskQuery().active().taskAssignee("").processInstanceId("047ba2f9-7c85-11ee-9aa1-00e04c8d8f92").singleResult();
        for (Task task : list) {
            log.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
            Map<String, Object> map = new HashMap<>();
            taskService.complete(task.getId(), map);

            // 设置李四为下个环节的审批人
            Task currentTask = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).active().singleResult();
            taskService.addCandidateUser(currentTask.getId(), lisi);
        }
    }

    // 查询李四任务、李四处理通过之后，将候选人设置为王五
    @Test
    public void liSiTaskPass() {
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(lisi).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
            Map<String, Object> map = new HashMap<>();
            map.put("checkResult", "通过");
            taskService.complete(task.getId(), map);

            // 设置王五为下个环节的审批人
            Task currentTask = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).active().singleResult();
            taskService.addCandidateUser(currentTask.getId(), wangwu);
        }
    }

    @Test
    public void liSiTaskReject() {
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(lisi).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
            Map<String, Object> map = new HashMap<>();
            map.put("checkResult", "拒绝");
            taskService.complete(task.getId(), map);

            // 设置张三为下个环节的审批人
            Task currentTask = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).active().singleResult();
            taskService.addCandidateUser(currentTask.getId(), zhangsan);
        }
    }

    // 查询王五任务、王五处理通过
    @Test
    public void wangWuTaskPass() {
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(wangwu).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
            Map<String, Object> map = new HashMap<>();
            map.put("checkResult", "通过");
            taskService.complete(task.getId(), map);
        }
    }

    // 查询王五任务、王五处理拒绝
    @Test
    public void wangWuTaskReject() {
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(wangwu).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
            Map<String, Object> map = new HashMap<>();
            map.put("checkResult", "拒绝");
            taskService.complete(task.getId(), map);

            // 设置王五为下个环节的审批人
            Task currentTask = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).active().singleResult();
            taskService.addCandidateUser(currentTask.getId(), lisi);
        }
    }



    @Test
    public void queryProc() {
        //String  processDefinitionId = "T_1103:1:d9dfa26a-7c6f-11ee-b253-00e04c8d8f92";
        //BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        //taskService.addCandidateUser("asdasd","123");
        //System.out.println(bpmnModel);

        //runtimeService.createChangeActivityStateBuilder()
        //        .processInstanceId(proInstanceId)
        //        .moveActivityIdsToSingleActivityId(curTaskKeys, targetTaskKey)
        //        .changeState();


    }

    @Test
    public void queryHisTask(){
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(lisi).orderByTaskId().desc().list();

        for (Task task : list) {
            String taskDefinitionKey = task.getTaskDefinitionKey();
            BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
            Process mainProcess = bpmnModel.getMainProcess();

            FlowNode currentFlowElement = (FlowNode) mainProcess.getFlowElement(taskDefinitionKey, true);

            // 查询历史节点实例
            List<HistoricActivityInstance> activityInstanceList = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId())
                    .finished()
                    .orderByHistoricActivityInstanceEndTime().asc().list()
                    .stream()
                    // 只需要开始节点和人员任务节点
                    .filter(s-> BpmnXMLConstants.ELEMENT_TASK_USER.equals(s.getActivityType()) || BpmnXMLConstants.ELEMENT_EVENT_START.equals(s.getActivityType()))
                    .collect(Collectors.toList());

            System.out.println(activityInstanceList);
        }
    }

    @Test
    public void queryNextNodeType(){
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(lisi).orderByTaskId().desc().list();

        for (Task task : list) {
            String taskDefinitionKey = task.getTaskDefinitionKey();
            BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
            Process mainProcess = bpmnModel.getMainProcess();
            FlowNode currentFlowElement = (FlowNode) mainProcess.getFlowElement(taskDefinitionKey, true);
            System.out.println(currentFlowElement);
        }

    }

    @Test
    public void addApproveUser(){
        // 增加审批人张三
        taskService.addCandidateUser("d4623a77-7d10-11ee-87a2-00e04c8d8f92", zhangsan);
    }







}