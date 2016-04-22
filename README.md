[ ![Download](https://api.bintray.com/packages/yushaojian13/maven/Log/images/download.svg) ](https://bintray.com/yushaojian13/maven/Log/_latestVersion)

修改自 [logger](https://github.com/orhanobut/logger)

<img src='https://github.com/yushaojian13/Log/blob/master/doc/Log.png'/>

### 特性
* 极简调用
* 花式输出
* 支持跳转
* 任意对象
* 任意长度
* 线程信息
* 堆栈信息
* 格式化输出JSON、Map
* 保存到文件
* 控制打印级别
* 全局/单项设置

### 设置 (可选)
支持链式设置

```
// 全局设置
L.setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE) // 控制日志级别，默认为LogLevel.FULL
        .setTag(getString(R.string.app_name)) // 设置日志tag，默认为“Log”
        .hideThreadInfo() // 隐藏线程信息，默认显示
        .hidePositionInfo() // 隐藏位置信息，默认显示
        .hideDivider() // 隐藏分隔条，默认显示
        .methodCount(5) // 设置调用堆栈数，默认为2
        .methodOffset(1) // 设置调用堆栈偏移量，默认为0
        .saveToFile(true) // 保存日志到文件，默认不保存，如需保存，须同时设置savePath和saveFilename
        .setSavePath(ContextCompat.getExternalFilesDirs(this, null)[0].getAbsolutePath()) // 日志文件路径，默认为空，不保存
        .setSaveFilename("Log"); // 日志文件名，默认为空，不保存

```
也可以单独设置


```
L.setTag(getString(R.string.app_name));
L.hideThreadInfo();
L.setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
```

### Dependency
build.gradle:

```
compile 'com.ysj.log:log:1.0.9'
```
