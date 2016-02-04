package app;

import json.JSONParser;
import json.Visitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Riz
 */

public class MainTest {

  @Test
  public void parseTest() throws Exception{
    InputStream bis = new FileInputStream(new File("src/main/resources/employees.json"));

    ANTLRInputStream input = new ANTLRInputStream(bis);
    Lexer lexer = new json.JSONLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    JSONParser parser = new json.JSONParser(tokens);
    parser.setErrorHandler(new BailErrorStrategy());
    JSONParser.JsonContext json = parser.json();

    Visitor visitor = new Visitor();
    Object map =  visitor.visitJson(json);
    System.out.println(map);
  }
}
