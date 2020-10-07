package org.survey.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/groups")
public class GroupsController {

   @RequestMapping("")
    String projects() {
        return "groups/groupManagement";
    }


}

