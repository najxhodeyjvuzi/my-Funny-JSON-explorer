# 设计模式

> 姓名：黄梓宏
>
> 学号：21307355



#### 概述

这次作业主要用到了四种设计模式，分别是工厂方法，抽象工厂，建造者，以及组合模式。

四种不同设计模式的实现主要分别放在了不同的代码目录下。代码结构参照如下：

![image-20240610160857959](https://raw.githubusercontent.com/najxhodeyjvuzi/Personal-Image-Hosting/main/image-20240610160857959.png)

其中，`/builder`主要实现建造者模式，`/composite`主要用于实现组合模式，`patterns`主要用于最终输出风格的确定，这里采用抽象工厂实现，而`printer`则是对应的打印器，使用到了工厂方法。详细的介绍见下模块。



#### 具体实现

- ##### 抽象工厂模式

  在这里，最终输出的结果涉及到两类关键属性，一个是**风格**，一个是使用到的**图标族**，两者存在交叉关系，即同一风格可以使用不同图标族，同一图标族可以使用不同风格。因此，在实现这一部分功能时，采用抽象工厂模式，设计类图如下：

  <img src="https://raw.githubusercontent.com/najxhodeyjvuzi/Personal-Image-Hosting/main/image-20240610173120772.png" alt="image-20240610173120772" style="zoom: 75%;" />

  这里按照**风格**分开两类，一类是树形，一类是矩形，而后在向下细分两类图标族。

  实现一个**抽象工厂接口**，包括两个方法，分别创建两种**风格**的类。

  两个具体工厂对抽象工厂接口进行实现，分别负责**两种不同图标族的创建**。

  

  不过值得一提到是，这是因为题目中要求使用抽象工厂，所以这里才采用抽象工厂。实际上，如果要采用`config.yml`实现图标族的自动装配的话，这里通过抽象工厂进行实现的话会带来很多多的麻烦。

  我认为更好地方法是简单工厂，或者工厂方法，将图标族作为参数传入到工厂中，作为创建对象的参数。这样，新增或减少图标族都不影响类的实现，同时可以用数字等有序编号表示每一个图标族，灵活性更强。

  

- ##### 组合模式

  Json结构的输入文件实际上是很契合组合模式的应用场景的，因此这里读取到输入的json文件后，采用组合模式，分别用分支和叶子节点的结构，进行json文件的结构化存储。

  实现类图如下：

  ![image-20240610172442972](https://raw.githubusercontent.com/najxhodeyjvuzi/Personal-Image-Hosting/main/image-20240610172442972.png)

首先定义了一个`Component`接口，由`Leaf`类和`Composite`类进行实现。

接口需要考虑到叶子和分支节点的不同情况，如果是叶子节点，那就是`key-value`对，如果是分支节点，虽然一样是`key-value`对，但是其`value`也是由一个可能包含多个`k-v`对的`map`构成。

除此之外，为了适配之后的结果输出，每个节点还需要记录一些必要的信息，用于判断当前节点是否叶子节点，是否是其父亲的第一个子节点或最后一个子节点等，涉及到的成员变量有`isFist`，`isLast`，`isLeaf`。

然后到两种不同的节点类，需要有选择性地对接口的方法进行实现，如`Leaf`就无需实现`add()`，而`Composite`中的`value`就没有用处。

`Map2Component`类只有一个静态方法`buildComponent`，用于将一个Map`(已经从文件中将一个json文件通过gson转换成了map结构)`转换成组合模式存储的结构。



- ##### 工厂方法模式

  这个思路比较清晰，主要是有两个接口`PrinterFactory`和`Printer`，然后两个接口都分别有两个具体实现。

  由于矩形输出相对树形输出较为复杂，所以`RectanglePrinter`的方法比较多。

  工厂方法模式完全符合**开闭原则**，每次增加或减少printer完全不影响原有的代码。

  ![image-20240610173541102](https://raw.githubusercontent.com/najxhodeyjvuzi/Personal-Image-Hosting/main/image-20240610173541102.png)

  

- #### 建造者模式

  建造者模式主要起到的是将整个流程标准化的作用，即我无需关心具体的建造过程，只需要知道建造者，既可以通过建造者的产品，去执行一套工作流程。

  在本题的情景中，主要就是通过将`AbstractBuilder`分别实现为对应两种风格的不同具体`builder`。两种不同的builder对与process有不同的构建方式，但是`director`并不需要知道细节，只需要调用`builder`的建造函数，就完成了`process`的搭建.

  <img src="https://raw.githubusercontent.com/najxhodeyjvuzi/Personal-Image-Hosting/main/image-20240610171737946.png" alt="image-20240610171737946" style="zoom:75%;" />

`process`的实现见下：

```java
public class Process {
    public Component jsonRoot;

    public TreePattern treePattern;
    public RectanglePattern rectanglePattern;

    public Printer printer;

    public void print() {
        Stack<Integer> stack = new Stack<>();
        printer.print(jsonRoot, treePattern!= null ? treePattern.composite : rectanglePattern.composite
                , treePattern != null ? treePattern.leaf : rectanglePattern.leaf, -1, stack);
    }
}
```

可以知道，process只有一个抽象的`Component`，一个抽象的`Printer`，以及两个`Pattern`，也就是说`process`可以交由不同的`builder`进行创建。

而且，在调用Printer时，也是多态的。



- ##### 主类实现

  因为最终需要将项目打包成命令行工具，因此，这里采用的是picocli的依赖，用于快速便捷地搭建命令行配置。

  主类中主要需要关注的，是`run()`函数，其包含了整个项目的整体逻辑。即先读取json文件，然后将json文件转换为组合模式存储的形式，之后根据传入的参数，指定相应的`builder`，然后交由`director`进行操作。

```java
@CommandLine.Command(name = "FJE", mixinStandardHelpOptions = true, version = "FJE 1.0",
        description = "FJE - JSON to Map")
public class FJE implements Runnable{

    @CommandLine.Option(names = {"-f", "--file"}, description = "Path to JSON file", required = true)
    private String file;

    @CommandLine.Option(names = {"-s", "--style"}, description = "Style of output", required = true)
    private String style;

    @CommandLine.Option(names = {"-i", "--icon"}, description = "Icon family to use", required = true)
    private String icon;

    @Override
    public void run() {
        JsonReader jsonReader = new JsonReader();
        Map map = jsonReader.jsonToMap(file);

        Component root = new Composite("root",true,true);

        // 使用递归构建树形结构
        Map2Component.buildComponent(root, map);

        AbstractBuilder builder = null;
        if (style.equals("tree")) builder = new TreeBuilder();
        if (style.equals("rectangle")) builder = new RectangleBuilder();

        Director director = new Director(builder);
        director.direct(root,icon);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new FJE()).execute(args);
        System.exit(exitCode);
    }
}
```

director的工作内容如下：

> 1. （选做）从IconFaminly.yml中读取图标族。可以更换不同的图标族。
> 2. 即设置图形样式，这里会调用到抽象工厂。
> 3. 设置Printer，这里会调用到工厂方法。
> 4. 获取builder建造好的产品。
> 5. 使用产品的print功能进行输出

```java
public void direct(Component root, String icon)  {
    builder.setJsonRoot(root);

    Yaml yaml = new Yaml(new Constructor((List.class)));
    FileInputStream inputStream = null;
    try {
        inputStream = new FileInputStream("src/main/java/org/example/resources/IconFamily.yml");
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }
    List<Map<String, String>> list = yaml.load(inputStream);
    ArrayList<Icon> iconList = new ArrayList<>();

    for (Map<String, String> map : list) {
        iconList.add(new Icon(map.get("composite"), map.get("leaf")));
    }

    builder.setPattern(icon, iconList);
    builder.setPrinter();

    Process process = builder.getProcess();
    process.print();

}
```



- ##### 命令行工具化

  这里采用的是打包工具`maven-assembly-plugin`，将项目打包成`fat jar`，然后通过编写bash，放在`$PATH$`目录下，实现命令行使用：

  ```bash
  #!/bin/bash
  java -jar /home/hwang/Programming/006-SoftwareEngineering/hw3/jar/hw3-1.0-SNAPSHOT-jar-with-dependencies.jar "$@"
  ```

  



#### 结果：

![image-20240610180851752](https://raw.githubusercontent.com/najxhodeyjvuzi/Personal-Image-Hosting/main/image-20240610180851752.png)

![image-20240610180903498](https://raw.githubusercontent.com/najxhodeyjvuzi/Personal-Image-Hosting/main/image-20240610180903498.png)





#### github源码

https://github.com/najxhodeyjvuzi/my-Funny-JSON-explorer.git