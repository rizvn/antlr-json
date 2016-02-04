package json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Visitor converts json to Java Hashmap
 * Created by Riz
 */
public class Visitor extends json.JSONBaseVisitor{

  static class Entry{
    String key;
    Object value;
  }

  //START PARSING FROM HERE
  public Object visitJson(json.JSONParser.JsonContext ctx) {
    if(ctx.array() != null){ //if array recognise
      return visitArray(ctx.array());
    }
    else if(ctx.object() != null){ //if object recognised
      return visitObject(ctx.object());
    }
    throw new RuntimeException("Invalid json");
  }

  public Object visitObject(json.JSONParser.ObjectContext ctx) {
    Map<String,Object> map = new HashMap<>();
    ctx.pair().forEach(pair -> {  //for each pair e
      Entry entry = visitPair(pair);
      map.put(entry.key, entry.value);
    });
    return map;
  }

  public Entry visitPair(json.JSONParser.PairContext ctx) {
    Entry entry = new Entry();
    entry.key = ctx.STRING().getText().replaceAll("\"","");
    entry.value = visitValue(ctx.value());
    return entry;
  }

  public List<Object> visitArray(json.JSONParser.ArrayContext ctx) {
    List<Object> values = new ArrayList<>();
    ctx.value().forEach( val ->
      values.add(visitValue(val))
    );
    return values;
  }

  public Object visitValue(json.JSONParser.ValueContext ctx) {
    if (ctx.STRING() != null){
      return ctx.STRING().getText().replaceAll("\"","");
    }
    else if(ctx.NUMBER()!=null){
      return  ctx.NUMBER().getText().replaceAll("\"","");
    }
    else if(ctx.getChildCount() == 0){
      return ctx.getText().replaceAll("\"","");
    }
    else if(ctx.array() != null) {
      return visitArray(ctx.array());
    }
    else if(ctx.object() != null){
      return visitObject(ctx.object());
    }
    else {
      throw new RuntimeException("Invalid symbol "+ ctx.getRuleIndex() + " at position: " + ctx.depth() + ctx.start) ;
    }
  }
}
