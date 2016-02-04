package json;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Riz
 */
public class JsonToJavaTest {

  @Test
  public void testToList() throws Exception {
    List<Object> list = JsonToJava.toList("[\"a\", \"b\", \"c\"]");
    Assert.assertTrue(list.size()==3);
  }

  @Test
  public void testToMap() throws Exception {
    Map<String, Object> map = JsonToJava.toMap("{\"a\":\"1\", \"c\": \"2\"}");
    Assert.assertTrue(map.containsKey("a"));
  }
}