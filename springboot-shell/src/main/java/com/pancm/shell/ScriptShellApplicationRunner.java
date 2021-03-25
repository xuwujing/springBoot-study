package com.pancm.shell;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.shell.jline.ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED;

/**
 * @author pancm
 * @Title: springboot-shell
 * @Description:
 * InteractiveShellApplicationRunner引导Shell REPL。它建立了JLine基础架构，并最终调用Shell.run()
 *
 * ScriptShellApplicationRunner查找以开头的程序参数，并@假定它们是本地文件名，
 * 并尝试运行这些文件中包含的命令（与script命令具有相同的语义），然后退出进程（通过有效地禁用InteractiveShellApplicationRunner，请参见下文）。
 * @Version:1.0.0
 * @Since:jdk1.8
 * @date 2021/3/25
 */
@Order(InteractiveShellApplicationRunner.PRECEDENCE - 100) // Runs before InteractiveShellApplicationRunner
public class ScriptShellApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<File> scriptsToRun = args.getNonOptionArgs().stream()
                .filter(s -> s.startsWith("@"))
                .map(s -> new File(s.substring(1)))
                .collect(Collectors.toList());

        ConfigurableEnvironment environment = new StandardEnvironment();
        boolean batchEnabled = environment.getProperty(SPRING_SHELL_SCRIPT_ENABLED, boolean.class, true);

        if (!scriptsToRun.isEmpty() && batchEnabled) {
            InteractiveShellApplicationRunner.disable(environment);
            for (File file : scriptsToRun) {
//                try (Reader reader = new FileReader(file);
//                     FileInputProvider inputProvider = new FileInputProvider(reader, parser)) {
//                    shell.run(inputProvider);
//                }
            }
        }
    }

}
