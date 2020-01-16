package kr.co.lunasoft.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class GatewayController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value = "/{endpoint}/{path}")
	public Map<String, Object> callGateway(@PathVariable String endpoint, @PathVariable String path) {
		String reqUrl = "http://" + endpoint + "-service/" + endpoint + "/" + path;

		Object output = restTemplate.getForObject(reqUrl, Object.class);
		log.info("" + output);

		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("code", "100200");
		obj.put("msg", "success");
		obj.put("data", output);
		return obj;
	}

}
