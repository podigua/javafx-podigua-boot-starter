# Spring Boot JavaFX starter

方便的将Spring boot 与 JavaFX结合,可以方便的在JavaFx中使用Spring boot的能力

# Maven

添加以下依赖

```xml
<dependency>
    <groupId>io.github.podigua</groupId>
    <artifactId>javafx-podigua-boot-starter</artifactId>
    <version>1.0.3</version>
</dependency>
```

# 用法

创建一个类,继承`AbstractJavafxApplication`,然后调用`launch`方法启动`JavaFX`程序

## 用法1

使用普通方法启动界面

```java
@SpringBootApplication
public class TestApplication extends AbstractJavafxApplication {
    public static void main(String[] args) {
        launch(TestApplication.class, args);
    }

    @Override
    protected void ready(Stage stage) {
        VBox root = new VBox();
        Button button = new Button("测试");
        button.setOnAction(event -> {
            Alert alert = new Alert(AlertType.NONE, "点击了按钮", ButtonType.OK);
            alert.showAndWait();
        });
        root.getChildren().add(button);
        Scene scene = new Scene(root, 1000, 750);
        stage.setScene(scene);
        stage.show();
    }
}
```

## 用法2

使用Fxml启动界面

### 启动类
`FxmlService` 封装了加载`fxml`文件的方法,并获取`spring`容器中的对应的`bean`

**需要在fxml文件中声明`fx:controller`**
```java

@SpringBootApplication
public class TestApplication extends AbstractJavafxApplication {
    public static void main(String[] args) {
        launch(TestApplication.class, args);
    }

    @Autowired
    private FxmlService fxmlService;

    @Override
    protected void ready(Stage stage) {
        Parent parent = fxmlService.load("fxml/index.fxml");
        stage.setScene(new Scene(parent, 1000, 750));
        stage.show();
    }
}
```

### fxml
路径为:`fxml/index.fxml`
```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.Button?>
<?import javafx.scene.layout.AnchorPane?>
<AnchorPane xmlns="http://javafx.com/javafx"
            xmlns:fx="http://javafx.com/fxml"
            fx:controller="com.example.javafx.IndexController"
            prefHeight="400.0" prefWidth="600.0">
    <Button text="测试" onAction="#test"></Button>
</AnchorPane>
```

### controller
`controller` 需要添加`@FXMLController`使`controller`注册为`spring`的`bean`

```java

@FXMLController
public class IndexController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize");
    }

    public void test(ActionEvent event) {
        Alert alert = new Alert(AlertType.NONE, "点击了按钮", ButtonType.OK);
        alert.showAndWait();
    }
}
```

# 动画

spring boot 加载过程较慢,spring加载过程中会展示一个动画,动画默认由`DefaultSplashScreen`提供,默认可见

创建一个类实现`SplashScreen`可以修改是否可见,以及图片的选择,若不以图片的方式展示,可以自行定义`getParent`方法

```java
public class ImageSplashScreen implements SplashScreen {

    @Override
    public boolean visible() {
        return true;
    }

    @Override
    public String getImagePath() {
        return "start.png";
    }
}
```

启动类调用`launch`的重载方法

```java
@SpringBootApplication
public class TestApplication extends AbstractJavafxApplication {
    public static void main(String[] args) {
        launch(TestApplication.class, new ImageSplashScreen(), args);
    }

    @Autowired
    private FxmlService fxmlService;

    @Override
    protected void ready(Stage stage) {
        Parent parent = fxmlService.load("fxml/index.fxml");
        stage.setScene(new Scene(parent, 1000, 750));
        stage.show();
    }
}
```

# 国际化
实现接口`com.podigua.javafx.support.ResourceBundleLoader`,并设置为`spring`的`bean`
国际化配置文件放置在`resource`路径下,名称为`messages.properties`,`messages_zh_CN.properties`,其他语言自行配置
```java
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@FXMLController
public class MessageResourceBundleLoader implements ResourceBundleLoader {
    @Override
    public ResourceBundle get() {
        return ResourceBundle.getBundle("messages");
    }
}
```