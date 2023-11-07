# flowable_demo

### 流程文件

```xml-dtd
<?xml version="1.0" encoding="UTF-8"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:flowable="http://flowable.org/bpmn" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI" typeLanguage="http://www.w3.org/2001/XMLSchema" expressionLanguage="http://www.w3.org/1999/XPath" targetNamespace="http://www.flowable.org/processdef">
  <process id="T_1103" name="T_1103" isExecutable="true">
    <documentation>复杂业务模型</documentation>
    <startEvent id="startEvent1" flowable:formFieldValidation="true"></startEvent>
    <userTask id="sid-084A9469-8E89-4910-903E-941D536E27A4" name="申请人环节" flowable:formFieldValidation="true"></userTask>
    <sequenceFlow id="sid-503A2FCC-A4CD-4015-B66C-A7DBBB32952B" sourceRef="startEvent1" targetRef="sid-084A9469-8E89-4910-903E-941D536E27A4"></sequenceFlow>
    <userTask id="sid-E0C674C6-3860-4E83-8B00-51E34A3F6699" name="审批人A审批" flowable:formFieldValidation="true"></userTask>
    <sequenceFlow id="sid-0C580FA1-1346-4AAA-A58B-C4F7C5B0C80F" sourceRef="sid-084A9469-8E89-4910-903E-941D536E27A4" targetRef="sid-E0C674C6-3860-4E83-8B00-51E34A3F6699"></sequenceFlow>
    <exclusiveGateway id="sid-BDBA2DD9-4143-41B2-A7B9-37E68CA4E575"></exclusiveGateway>
    <sequenceFlow id="sid-D2089D21-3B78-43F7-B8C1-1F918ACD18DC" sourceRef="sid-E0C674C6-3860-4E83-8B00-51E34A3F6699" targetRef="sid-BDBA2DD9-4143-41B2-A7B9-37E68CA4E575"></sequenceFlow>
    <userTask id="sid-25644B62-9FE0-4226-843D-A2073B500BEF" name="审批人B审批" flowable:formFieldValidation="true"></userTask>
    <exclusiveGateway id="sid-E77BEA93-0D25-44BA-96EC-D2C0EA58E9A0"></exclusiveGateway>
    <sequenceFlow id="sid-0B7DAEF4-8967-4727-A9F5-495374B4C0CD" sourceRef="sid-25644B62-9FE0-4226-843D-A2073B500BEF" targetRef="sid-E77BEA93-0D25-44BA-96EC-D2C0EA58E9A0"></sequenceFlow>
    <endEvent id="sid-A099AA11-7517-4174-B4E1-C81FBA472236"></endEvent>
    <sequenceFlow id="sid-9B79D5C7-4CCC-4280-9B1A-39A252B69F24" name="通过" sourceRef="sid-BDBA2DD9-4143-41B2-A7B9-37E68CA4E575" targetRef="sid-25644B62-9FE0-4226-843D-A2073B500BEF">
      <conditionExpression xsi:type="tFormalExpression"><![CDATA[${checkResult=='通过'}]]></conditionExpression>
    </sequenceFlow>
    <sequenceFlow id="sid-E8C36C36-46FB-411B-ADF5-F57CDE32F597" name="通过" sourceRef="sid-E77BEA93-0D25-44BA-96EC-D2C0EA58E9A0" targetRef="sid-A099AA11-7517-4174-B4E1-C81FBA472236">
      <conditionExpression xsi:type="tFormalExpression"><![CDATA[${checkResult=='通过'}]]></conditionExpression>
    </sequenceFlow>
    <sequenceFlow id="sid-B883F2E4-97F1-4233-B637-C4D7C64C9F70" name="拒绝" sourceRef="sid-E77BEA93-0D25-44BA-96EC-D2C0EA58E9A0" targetRef="sid-E0C674C6-3860-4E83-8B00-51E34A3F6699">
      <conditionExpression xsi:type="tFormalExpression"><![CDATA[${checkResult=='拒绝'}]]></conditionExpression>
    </sequenceFlow>
    <sequenceFlow id="sid-0732AFDE-2638-42B5-B9D8-EEE69E4279CE" name="拒绝" sourceRef="sid-BDBA2DD9-4143-41B2-A7B9-37E68CA4E575" targetRef="sid-084A9469-8E89-4910-903E-941D536E27A4">
      <conditionExpression xsi:type="tFormalExpression"><![CDATA[${checkResult=='拒绝'}]]></conditionExpression>
    </sequenceFlow>
  </process>
  <bpmndi:BPMNDiagram id="BPMNDiagram_T_1103">
    <bpmndi:BPMNPlane bpmnElement="T_1103" id="BPMNPlane_T_1103">
      <bpmndi:BPMNShape bpmnElement="startEvent1" id="BPMNShape_startEvent1">
        <omgdc:Bounds height="30.0" width="30.0" x="60.0" y="163.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-084A9469-8E89-4910-903E-941D536E27A4" id="BPMNShape_sid-084A9469-8E89-4910-903E-941D536E27A4">
        <omgdc:Bounds height="80.0" width="100.0" x="175.0" y="138.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-E0C674C6-3860-4E83-8B00-51E34A3F6699" id="BPMNShape_sid-E0C674C6-3860-4E83-8B00-51E34A3F6699">
        <omgdc:Bounds height="80.0" width="100.0" x="375.0" y="138.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-BDBA2DD9-4143-41B2-A7B9-37E68CA4E575" id="BPMNShape_sid-BDBA2DD9-4143-41B2-A7B9-37E68CA4E575">
        <omgdc:Bounds height="40.0" width="40.0" x="585.0" y="158.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-25644B62-9FE0-4226-843D-A2073B500BEF" id="BPMNShape_sid-25644B62-9FE0-4226-843D-A2073B500BEF">
        <omgdc:Bounds height="80.0" width="100.0" x="735.0" y="138.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-E77BEA93-0D25-44BA-96EC-D2C0EA58E9A0" id="BPMNShape_sid-E77BEA93-0D25-44BA-96EC-D2C0EA58E9A0">
        <omgdc:Bounds height="40.0" width="40.0" x="930.0" y="158.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-A099AA11-7517-4174-B4E1-C81FBA472236" id="BPMNShape_sid-A099AA11-7517-4174-B4E1-C81FBA472236">
        <omgdc:Bounds height="28.0" width="28.0" x="1080.0" y="164.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="sid-0732AFDE-2638-42B5-B9D8-EEE69E4279CE" id="BPMNEdge_sid-0732AFDE-2638-42B5-B9D8-EEE69E4279CE">
        <omgdi:waypoint x="605.5" y="158.5"></omgdi:waypoint>
        <omgdi:waypoint x="605.5" y="35.0"></omgdi:waypoint>
        <omgdi:waypoint x="225.0" y="35.0"></omgdi:waypoint>
        <omgdi:waypoint x="225.0" y="138.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-503A2FCC-A4CD-4015-B66C-A7DBBB32952B" id="BPMNEdge_sid-503A2FCC-A4CD-4015-B66C-A7DBBB32952B">
        <omgdi:waypoint x="89.94999918773193" y="178.0"></omgdi:waypoint>
        <omgdi:waypoint x="174.9999999999364" y="178.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-0B7DAEF4-8967-4727-A9F5-495374B4C0CD" id="BPMNEdge_sid-0B7DAEF4-8967-4727-A9F5-495374B4C0CD">
        <omgdi:waypoint x="834.9499999999979" y="178.15090634441088"></omgdi:waypoint>
        <omgdi:waypoint x="930.439393939394" y="178.43939393939397"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-B883F2E4-97F1-4233-B637-C4D7C64C9F70" id="BPMNEdge_sid-B883F2E4-97F1-4233-B637-C4D7C64C9F70">
        <omgdi:waypoint x="950.5" y="197.444100249066"></omgdi:waypoint>
        <omgdi:waypoint x="950.5" y="339.0"></omgdi:waypoint>
        <omgdi:waypoint x="425.0" y="339.0"></omgdi:waypoint>
        <omgdi:waypoint x="425.0" y="217.95000000000002"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-E8C36C36-46FB-411B-ADF5-F57CDE32F597" id="BPMNEdge_sid-E8C36C36-46FB-411B-ADF5-F57CDE32F597">
        <omgdi:waypoint x="969.5095911949685" y="178.43356643356645"></omgdi:waypoint>
        <omgdi:waypoint x="1080.000082938008" y="178.04860604497966"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-0C580FA1-1346-4AAA-A58B-C4F7C5B0C80F" id="BPMNEdge_sid-0C580FA1-1346-4AAA-A58B-C4F7C5B0C80F">
        <omgdi:waypoint x="274.94999999997566" y="178.0"></omgdi:waypoint>
        <omgdi:waypoint x="375.0" y="178.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-9B79D5C7-4CCC-4280-9B1A-39A252B69F24" id="BPMNEdge_sid-9B79D5C7-4CCC-4280-9B1A-39A252B69F24">
        <omgdi:waypoint x="624.4976130653266" y="178.44692737430168"></omgdi:waypoint>
        <omgdi:waypoint x="735.0" y="178.1391364902507"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-D2089D21-3B78-43F7-B8C1-1F918ACD18DC" id="BPMNEdge_sid-D2089D21-3B78-43F7-B8C1-1F918ACD18DC">
        <omgdi:waypoint x="474.9499999999962" y="178.13836565096952"></omgdi:waypoint>
        <omgdi:waypoint x="585.4444444444445" y="178.44444444444446"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</definitions>
```



### flowable常用表

| 表名                | 备注             |
| ------------------- | ---------------- |
| act_ru_identitylink | 受理处理人表     |
| act_ru_task         | 运行任务表       |
| act_ru_variable     | 运行任务变量表   |
| act_ru_actinst      | 运行实例表       |
| act_hi_actinst      | 历史运行实例表   |
| act_hi_identitylink | 历史受理处理人表 |
| act_hi_procinst     | 历史流程实例表   |
| act_hi_taskinst     | 历史任务实例表   |
| act_hi_comment      | 历史任务修改记录 |
| act_hi_varinst      | 历史变量表       |
| act_re_procdef      | 流程定义表       |
| act_re_deployment   | 流程部署表       |



### 清空流程或任务

```sql
-- 删除所有运行数据
TRUNCATE TABLE act_ru_actinst;
TRUNCATE TABLE act_ru_deadletter_job;
TRUNCATE TABLE act_ru_entitylink;
TRUNCATE TABLE act_ru_event_subscr;
TRUNCATE TABLE act_ru_execution;
TRUNCATE TABLE act_ru_history_job;
TRUNCATE TABLE act_ru_identitylink;
TRUNCATE TABLE act_ru_job;
TRUNCATE TABLE act_ru_suspended_job;
TRUNCATE TABLE act_ru_task;
TRUNCATE TABLE act_ru_timer_job;
TRUNCATE TABLE act_ru_variable;

-- 删除所有历史数据
TRUNCATE TABLE act_hi_actinst;
TRUNCATE TABLE act_hi_attachment;
TRUNCATE TABLE act_hi_comment;
TRUNCATE TABLE act_hi_detail;
TRUNCATE TABLE act_hi_entitylink;
TRUNCATE TABLE act_hi_identitylink;
TRUNCATE TABLE act_hi_procinst;
TRUNCATE TABLE act_hi_taskinst;
TRUNCATE TABLE act_hi_tsk_log;
TRUNCATE TABLE act_hi_varinst;
```

