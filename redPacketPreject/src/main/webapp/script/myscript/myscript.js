// 计算可变参数之和
function abc(){
    var count=0;
    //js中有个变量arguments,可以访问所有传入的值
    for(var i=0; i<arguments.length; i++){
        count += arguments[i];
    }
    return count;
}
abc(2,5,6);


// 数组去重
function arrRemoval(arr) {
    var temp = new Array();
    if (arr !=null && arr.length > 0) {
        for (var i=0,len=arr.length;i<len;i++){
            if (!temp.includes(arr[i])) {
                temp.push(arr[i]);
            }
        }
    }
    return temp;
}

var arr = new Array(1, 1, 1);
arr = arrRemoval(arr);