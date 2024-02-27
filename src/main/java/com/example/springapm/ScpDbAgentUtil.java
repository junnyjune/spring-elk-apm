package com.example.springapm;

import com.penta.scpdb.ScpDbAgent;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ScpDbAgentUtil {
  
  private static ScpDbAgent agt;
  private static String filePath;
  private static String key;
  
  public static void setFilePath(String filePath) {
    ScpDbAgentUtil.filePath = filePath;
  }
  
  public static void setKey(String key) {
    ScpDbAgentUtil.key = key;
  }
  
  
  @PostConstruct
  private void init() throws InterruptedException {
    agt = new ScpDbAgent();
  }
  
  /**
   * plainText가 null인 경우 ScpEncB64의 반환값은 String.empty가 반환된다
   *
   * @param plainText
   * @return
   */
  public static String encode(String plainText) {
    return agt.ScpEncB64(filePath, key, plainText);
  }
  
  /**
   * encodedText가 null인 경우 ScpDecB64의 반환값은 String.empty가 반환된다.
   *
   * @param encodedText
   * @return
   */
  public static String decode(String encodedText) {
    return agt.ScpDecB64(filePath, key, encodedText);
  }
  
  @Value("${scpdb.file_path}")
  public void injectFilePath(String filePath) {
    ScpDbAgentUtil.filePath = filePath;
  }
  
  @Value("${scpdb.key}")
  public void injectKey(String key) {
    ScpDbAgentUtil.key = key;
  }
}
