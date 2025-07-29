package com.codelab.backend.service;

import com.codelab.backend.request.UserCodeRequest;
import com.codelab.backend.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CompilationService {
    record TestCase(String input , String expected){}
    public String compileJava(UserCodeRequest userCodeRequest) throws IOException {

        String userCode = userCodeRequest.getCode();
        BufferedWriter solutionWriter = new BufferedWriter(new FileWriter("./Dockers/java-compiler/Solution.java"));
        solutionWriter.write(userCode);
        solutionWriter.close();

        StringBuilder mainCode = new StringBuilder("public class Main {\npublic static void main(String[] args) {\nSolution s = new Solution();\n");

        List<TestCase> testCases = List.of(
                new TestCase("10, 20", "30"),
                new TestCase("5, 5", "10"),
                new TestCase("100, 50", "150")
        );

        for (int i = 0; i < testCases.size(); i++) {
            mainCode.append(String.format("System.out.println(\"TC%d:\" + s.addTwoNumbers(%s));\n", i, testCases.get(i).input()));
        }
        mainCode.append("}\n}");

        BufferedWriter mainWriter = new BufferedWriter(new FileWriter("./Dockers/java-compiler/Main.java"));
        mainWriter.write(mainCode.toString());
        mainWriter.close();

        String hostPath = System.getProperty("user.dir") + "/Dockers/java-compiler";
        ProcessBuilder processBuilder = new ProcessBuilder(
                "docker", "run", "--rm", "-v", hostPath+":/app", "java-runner"
        );

        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        return "sucess";
    }
}
