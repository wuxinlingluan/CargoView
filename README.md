# CargoView
[![](https://camo.githubusercontent.com/8cb994f6c4a156c623fe057fccd7fb7d7d2e8c9b/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6c6963656e73652d417061636865253230322d3445423142412e737667)](https://www.apache.org/licenses/LICENSE-2.0.html) [![](https://camo.githubusercontent.com/7497225d65b4b00fd03b44fd9a4b77fcbb23fd16/68747470733a2f2f6a69747061636b2e696f2f762f6875616e6779616e62696e2f736d6172745461626c652e737667)](https://jitpack.io/private#wuxinlingluan/GoodShelfView/v1.0)

>此控件为一个项目中用到的,要求做一个货架与货物对应的界面效果,于是找UI做了个背景图和货物图片,做出这样的效果
### 引用方式

>添加 JitPack repository 到你的build文件
```
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```  
 >增加依赖
  ```
 dependencies {
	        compile 'com.github.wuxinlingluan:GoodShelfView:v1.0'
	}
 ```
  >使用
   ```
     <com.github.view.GoodShelfView
        android:id="@+id/gf1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
   ```
  >设置货架数据
   ```
     GoodShelfView gf1 = (GoodShelfView) findViewById(R.id.gf1);//初始化
     List<PointBean> pointList1=new ArrayList<>();//集合
     gf1.setGoodsList(pointList1);//设置数据
   ```
