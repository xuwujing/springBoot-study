package com.pancm.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * @author pancm
 * @Title: springboot-shell
 * @Description: shell命令实现
 * @Version:1.0.0
 * @Since:jdk1.8
 * @date 2021/3/25
 */
@ShellComponent
public class MyCommands {

    /**
    *  运行程序，在控制台输入 add 1 2，即可看到打印3
    **/
    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }

    @ShellMethod(value = "Add numbers.", key = "sum")
    public int add2(int a, int b) {
        return a + b;
    }

    /**
    * 自定义命名参数键
     * 如上所示，为命名参数派生键的默认策略是使用方法签名的java名称，并在其前面加上两个破折号（--）。可以通过两种方式自定义：
     * 要更改整个方法的默认前缀，请使用注释的prefix()属性 @ShellMethod
     * 要以每个参数的方式覆盖整个键，请使用注释对参数进行@ShellOption注释。
    **/
    @ShellMethod(value = "Display stuff.", prefix="-")
    public String echo(int a, int b, @ShellOption("--third") int c) {
        return String.format("You said a=%d, b=%d, c=%d", a, b, c);
    }

}
