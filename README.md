[ ![Download](https://api.bintray.com/packages/yushaojian13/maven/Log/images/download.svg) ](https://bintray.com/yushaojian13/maven/Log/_latestVersion)

A more convenient Android log

### Dependency
build.gradle:

```
compile 'com.ysj.log:log:1.0.1'
```


### Settings (optional)
```
L.setTag(getString(R.string.app_name)) // default tag is "Log"
 .hideThreadInfo()
 .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
```
equals

```
L.setTag(getString(R.string.app_name));
L.hideThreadInfo();
L.setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
```

### Usage

```
// quite simple
L.d("hello");

// specify a special tag
L.d("LOG", "hello");

// sometimes we just want to see which method was called, without any messages.
L.d();

// JSON is printed pretty
L.d(longJSON);
L.d(jsonArray);

L.d(list);
```