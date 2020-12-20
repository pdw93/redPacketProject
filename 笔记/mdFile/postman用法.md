

## [官方文档](https://learning.postman.com/docs/postman/api-documentation/documenting-your-api/)

## 设置变量

```javascript
// 设置全局变量 优先级：1 低
pm.globals.set("variable_key", "globals");
// 设置集合变量	优先级：2
pm.collectionVariables.set("variable_key", "collectionVariables");
// 设置环境变量	优先级：3
pm.environment.set("variable_key", "environment");
// 设置局部变量， 仅运行时可用	优先级：4 高
pm.variables.set("variable_key", "variables");
```

## 清楚全部变量

```javascript
// 清理局部变量
pm.variables.clear();

// 清理环境变量
pm.environment.clear();

// 清理集合变量
pm.collectionVariables.clear();

// 清理全局变量
pm.globals.clear();

```

## 清理单个变量

```javascript
// 清理局部变量
pm.variables.unset("variable_key");

// 清理环境变量
pm.environment.unset("variable_key");

// 清理集合变量
pm.collectionVariables.unset("variable_key");

// 清理全局变量
pm.globals.unset("variable_key");
```



## [Pre-reques Script](https://learning.postman.com/docs/postman/scripts/pre-request-scripts/) 使用

1. 获取请求参数

```javascript
var requesParams = pm.request.body.urlencoded;
requesParams.map(value => {
  pm.environment.set(value.key, value.value);
});
```

2. [发送请求](https://www.jianshu.com/p/9481387c7cd7)

```javascript
// 发送get请求
pm.sendRequest(pm.environment.get("janus-web") + "/applyorder/applyFirstRentApi/queryFirstRentApplyStatus.json?applyId=" + pm.environment.get("applyId"), function (err, response) {
    var result = response.json().data;
    pm.environment.set("xxx", result.xxx);
});

// 发送表单格式Post请求
const request1 = {
    url: pm.environment.get("janus-web") + "/applyorder/applyFirstRentApi/queryFirstRentApplyStatus.json",
    method: "POST",
    header: 'Souche-Security-Token: '+pm.environment.get("Souche-Security-Token"),  //注意要在Header中添加token
    body: {
        mode: 'urlencoded',  // 模式为表单url编码模式
        urlencoded: "applyId=" + pm.environment.get("applyId")
    }
};
// 发送JSON格式Post请求
const request1 = {
    url: pm.environment.get("janus-web") + "/applyorder/applyFirstRentApi/queryFirstRentApplyStatus.json",
    method: "POST",
    header: {
        'Content-Type': 'application/json',
        'Souche-Security-Token': pm.environment.get("Souche-Security-Token")
    },  //注意要在Header中添加token
    body: {
        mode: 'raw',  // 模式为表单url编码模式
        raw: JSON.stringify({
            applyId: pm.environment.get("applyId")
        }) //要将JSON对象转为文本发送
    }
};
pm.sendRequest(request1, function (err, response) {
    var result = response.json().data;
    pm.environment.set("bizNo", result.bizNo);
    pm.environment.set("orderNo", result.orderNo);

});
```



## [test](https://learning.postman.com/docs/postman/scripts/test-scripts/) 使用

1. 获取请求参数

```javascript
var requesParams = pm.request.body.urlencoded;
requesParams.map(value => {
  pm.environment.set(value.key, value.value);
});
```



2. 获取返回值

```javascript
// 把responseBody转为json字符串
var result = response.json().data;
// 设置环境变量token，供后面的接口引用
pm.environment.set("applyId", result.applyId);

```



