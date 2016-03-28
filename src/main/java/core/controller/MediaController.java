package core.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Adrian on 17/10/2015.
 */
@Controller
@RequestMapping(value = "/media")
public class MediaController {

    @RequestMapping(method = RequestMethod.GET, value = "/test/sound")
    public String testSound() {
        return "media/test/sound/sound";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test/video")
    public String testVideo() {
        return "media/test/video/video";
    }
}
