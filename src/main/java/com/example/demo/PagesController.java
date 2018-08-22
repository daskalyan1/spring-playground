package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/math")
public class PagesController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/pi")
    public double getPi() {
        return Math.PI;
    }

    /*@GetMapping("/calculate")
    public int calculateValue(
        @RequestParam(value = "operation", defaultValue = "") String operation,
        @RequestParam("x") int x,
        @RequestParam("y") int y)
    {
        switch (operation.trim().toLowerCase())
        {
            case "":
            case "add":
                return x + y;
            case "subtract":
                return x - y;
            case "multiply":
                return x*y;
            case "divide":
                return x/y;
            default:
                return 0;
        }
    }*/

    /*@PostMapping("/sum")
    public int calculateSum(@RequestParam MultiValueMap<String, String> queryString) {
        int sum = 0;
        for (String key: queryString.keySet() ) {
            List<String> queryParam = queryString.get(key);
            for (String queryValue: queryParam) {
               sum += Integer.parseInt(queryValue);
            }
        }
        return sum;
    };*/

    @GetMapping("/calculate")
    public int calculateValueUsingClass(MathService mathService) {
        switch (mathService.getOperation().trim()) {
            case "":
            case "add":
                return mathService.getX() + mathService.getY();
            case "subtract":
                return mathService.getX() - mathService.getY();
            case "multiply":
                return mathService.getX() * mathService.getY();
            case "divide":
                return mathService.getX() / mathService.getY();
            default:
                return 0;
        }
    }

    @PostMapping("/sum")
    public int calculateSumUsingClass(MathServiceSum mathServiceSum) {
        int sum = 0;
        for (String key : mathServiceSum.getQueryParam().keySet()) {
            List<String> queryParam = mathServiceSum.getQueryParam().get(key);
            for (String queryValue : queryParam) {
                sum += Integer.parseInt(queryValue);
            }
        }
        return sum;
    }

    /*@PostMapping("/volume/{length}/{width}/{height}")
    public String returnVolume(@PathVariable("length") Integer length, @PathVariable("width") Integer width, @PathVariable("height") Integer height){
        return String.format("The volume of a %sX%sX%s rectangle is %s", length, width, height, length*width*height);
    }*/

    /*@PostMapping("/volume/{length}/{width}/{height}")
    public String returnVolume(@PathVariable Map pathVariables){
        Integer length = Integer.parseInt(pathVariables.get("length").toString());
        Integer width = Integer.parseInt(pathVariables.get("width").toString());
        Integer height = Integer.parseInt(pathVariables.get("height").toString());
        return String.format("The volume of a %sX%sX%s rectangle is %s", length, width, height, length*width*height);
    }*/

    @PostMapping("/volume/{length}/{width}/{height}")
    public String returnVolume(Rectangle rectangle){
        return String.format("The volume of a %sX%sX%s rectangle is %s", rectangle.getLength(), rectangle.getWidth(), rectangle.getHeight(),                                       rectangle.getLength()*rectangle.getWidth()*rectangle.getHeight());
    }

    /*@PostMapping(value = "/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String calculateArea(@RequestParam Map formData){
        String type = formData.get("type").toString();
        String returnValue = "";
        if (type.equalsIgnoreCase("circle")){
            if (!formData.containsKey("radius"))
                returnValue = "Invalid";
            else{
                Integer radius = Integer.parseInt(formData.get("radius").toString());
                returnValue = String.format("Area of a circle with a radius of %s is %s", radius, Math.PI*radius*radius);}
        }

        if (type.equalsIgnoreCase("rectangle")){
            if (!formData.containsKey("width") || !formData.containsKey("height"))
                returnValue = "Invalid";
            else{
                Integer width = Integer.parseInt(formData.get("width").toString());
                Integer height = Integer.parseInt(formData.get("height").toString());
                returnValue =  String.format("Area of a %sX%s rectangle is %s", width, height, width*height);}
        }
        return returnValue;
    }*/

    @PostMapping(value = "/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String calculateAreaOfShape(Shape shape) {
        String type = shape.getType();
        String returnValue = "Invalid";

        if (type.equalsIgnoreCase("circle")) {
            if (shape.getRadius() > 0) {
                Integer radius = shape.getRadius();
                returnValue = String.format("Area of a circle with a radius of %s is %s", radius, Math.PI * radius * radius);
            }
        }

        if (type.equalsIgnoreCase("rectangle")) {
            if (shape.getHeight() > 0 && shape.getWidth() > 0)
                returnValue = String.format("Area of a %sX%s rectangle is %s", shape.getWidth(), shape.getHeight(), shape.getHeight() * shape.getWidth());
        }
        return returnValue;
    }
}
