import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author zhuzengpeng
 * @date 2018/1/10
 */
public class ClassPathTest {

    @Test
    public void test1() {
        String fileName = "config.xml";
        try {
            String pathName = "/templates/" + fileName;
            Resource resource = new ClassPathResource(pathName);
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = br.readLine();
                if (line == null){
                    break;
                }
                sb.append(line).append("\r\n");
            }
            if (is != null) {
                is.close();
            }
            if (br != null) {
                br.close();
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
