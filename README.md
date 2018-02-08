# GeneralPage

该工具是对无数据页面、加载页面、异常页面进行简单的封装，它支持的页面：

- 空数据页面
- 加载异常页面
- 正在加载页面

版本特性:
- v1.0.0（2018/2/8）
需要在XML中引入一个布局；
提供Fragment与Activity的基类，可以实现快速接入；

### 运行效果
[Demo运行效果](https://github.com/zfman/GeneralPage/wiki/Demo%E8%BF%90%E8%A1%8C%E6%95%88%E6%9E%9C) 

[下载Demo App](https://raw.githubusercontent.com/zfman/GeneralPage/master/extras/demo.apk)


### 简单使用

**Step 1：添加项目依赖**

在`build.gradle`文件中添加以下代码
```
compile 'com.zhuangfei:GeneralPage:1.0.0'
```

**Step 2：引入布局**

在需要的地方添加以下代码，当`helper_layout`显示出来时，它将撑满父级容器
```xml
<include layout="@layout/helper_layout"/>
```

**Step 3：快速使用**

用了能够更加方便的使用，你可以继承BaseActivity与BaseFragment。

如果你用的是`Activity`，那么你只要保证`getViewHelper()`的调用在`setContentView()`之后就不会出现空指针的情况(即使你在布局文件中没有包含`helper_layout`布局)，现在`Activity`的代码是这个样子的：
```java
public class ExampleActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        inits();
    }

    private void inits() {
	//getViewHelper().showSuccessView();
	//getViewHelper().showEmptyView();
	//getViewHelper().showErrorView("网络连接出错了!");
	getViewHelper().showLoadingView();
    }
}
```
如果你用的是`Fragment`，那么你要保证你的初始化方法都在`onViewCreated()`方法中，并且在初始化前调用一下父类的`onViewCreated()`方法，之后就可以尽情的调用`ViewHelper`对象的方法了，现在`Fragment`的代码是这个样子的：
```java
public class TabFragment1 extends BaseFragment {

    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tab1, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
	//这句不能少，保证ViewHelper对象可以获取到View
        super.onViewCreated(view, savedInstanceState);
        inits();
    }

    private void inits() {
        getViewHelper().showLoadingView();
    }

}
```
### 不使用基类
BaseActivity以及BaseFragment让整个过程简单了一点，如果你不想使用BaseActivity以及BaseFragment，那么也是可以的。

在BaseActivity以及BaseFragment中其实只做了初始化ViewHelper对象，目的是简化初始化ViewHelper对象，如果不使用BaseActivity、BaseFragment，那么应该先初始化ViewHelper对象，然后就可以随意调用它的方法了。

Actiivty的代码是这样的：

```java
public class ExampleActivity extends AppCompatActivity{

    private ViewHelper mViewHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        //获取View以及初始化ViewHelper
        View view=((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        mViewHelper=new ViewHelper(view);
        
        inits();
    }

    private void inits() {
        //加载页面
        mViewHelper.showLoadingView();
    }
}
```
Fragment的代码是这样的：
```java
public class TabFragment1 extends Fragment {

    View mView;
    ViewHelper mViewHelper;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tab1, container, false);
        mViewHelper=new ViewHelper(mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inits();
    }

    private void inits() {
        mViewHelper.showLoadingView();
    }

}
```

### API

ViewHelper是核心类，在使用前需要初始化(使用基类则不需要)：

```java
mViewHelper=new ViewHelper(mView);
```
提供四个方法：
```java
//调用后将隐藏提示布局
showSuccessView()

//调用后显示空数据页面
showEmptyView()

//调用后显示错误信息
showErrorView(String msg)

//调用后显示加载页面
showLoadingView()
```



### 自定义布局

现有的几个布局都很简陋，如果它不能满足需求，你可以自定义，假设目前要自定义加载页面，其他几个页面保持不变。

上文说了，我们要引用`helper_layout`，那么这个文件是什么？源码如下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/helper_container"
    android:orientation="vertical">
    
    <include layout="@layout/helper_loading" />
    <include layout="@layout/helper_error" />
    <include layout="@layout/helper_empty" />
    
</LinearLayout>
```
可见这三个布局依次为加载页面、错误页面、空数据页面，那么自定义布局首先需要定义一个自己的`helper_layout.xml`文件，起个名字就叫`custom_helper_layout.xml`,，然后用自定义的页面替换原有的某个页面，不需要替换的保持不变。

具体步骤如下：
- 创建一个布局文件 `custom_helper_layout.xml`，其代码如下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/helper_container"
    android:orientation="vertical">

    <include layout="@layout/custom_helper_loading" />

    <!--不需要变化-->
    <include layout="@layout/helper_error" />
    <include layout="@layout/helper_empty" />


</LinearLayout>
```
以下是`custom_helper_loading.xml`的内容
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/layout_loading"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical">

    <ProgressBar
        android:layout_width="40dp"
        android:layout_height="40dp" />
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:textColor="@color/app_gray"
        android:text="这是自定义的加载页面~~"/>
</LinearLayout>
```
最后在引用布局的时候使用自定义的布局即可，示例如下：
```xml
<include layout="@layout/custom_helper_layout"/>
```

自定义布局时，自定义页面的根`View`必须为LinearLayout且`ID`必须为以下值：
| 序号 | 页面 | `id` |
|---|---|---|
|1 | 加载页面 | `layout_loading` |
|2 |错误页面 | `layout_error `|
|3 |空数据页面 |` layout_empty`|

如果是错误页面，那么在该布局内应该包含一个`id`为`textview_error`的`TextView`控件

### License

MIT License

Copyright (c) 2017 壮飞

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.



