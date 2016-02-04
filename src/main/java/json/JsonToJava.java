package json;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Created by Riz
 */
public class JsonToJava {

  public static List<Object> toList(String json){
    return (List<Object>) parse(json);
  }

  public static Map<String, Object> toMap(String json){
    return (Map<String, Object>) parse(json);
  }

  private static Object parse(String json){
    try
    {
      InputStream bis = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
      ANTLRInputStream input = new ANTLRInputStream(bis);

      Lexer lexer = new json.JSONLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);

      json.JSONParser parser = new json.JSONParser(tokens);
      parser.setErrorHandler(new BailErrorStrategy());
      json.JSONParser.JsonContext jsonCtx = parser.json();

      Visitor visitor = new Visitor();
      return visitor.visitJson(jsonCtx);
    }
    catch (Exception aEx)
    {
      throw new RuntimeException(aEx);
    }
  }
}
