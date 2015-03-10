《Java应用架构设计》源代码示例

[Chapter 9][Section 5][Code 2] - 独立部署 - 独立版本
 - 修改了Employee中的pay()方法，移除了对Payroll的依赖
 - 新增PayrollRunner接口，通过实现类去调用Payroll进行具体计算
 - 新的Employee中pay()与PayrollRunner关联
 - 打破employee.jar对payroll.jar的依赖
