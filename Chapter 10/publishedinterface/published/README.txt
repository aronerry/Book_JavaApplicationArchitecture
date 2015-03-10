《Java应用架构设计》源代码示例

[Chapter 10][Section 1][Code 2] - 发布接口 - 接口版本
 - Customer与Order由类class转变为接口impl，供模块外部调用
 - 新增DefaultCustomer与DefaultOrder以实现具体功能，只供模块内部调用
 - 原setDiscountAmount()方法现只能内部引用，不向外开放
