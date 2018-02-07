package bis332_spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private static String webpage = "<html><body>title</body>";

    @RequestMapping("/calculate")
    public String calculate(@RequestParam(value="a", defaultValue = "0") String name) {
        return webpage;
    }

}
