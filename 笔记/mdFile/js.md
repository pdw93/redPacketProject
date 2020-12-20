[TOC]



## js实现sleep

```javascript
function sleep (time) {
  return new Promise((resolve) => setTimeout(resolve, time));
}

await sleep(2000).then(() => {
  // do something
  console.log("sleep end!");
});
console.log("before or after");
```



## js抛异常

```javascript
throw new Error("自定义错误！");
```



