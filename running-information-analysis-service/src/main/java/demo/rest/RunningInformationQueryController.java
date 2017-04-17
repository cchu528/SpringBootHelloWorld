package demo.rest;

import demo.domain.RunningInformation;
import demo.service.RunningInformationService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class RunningInformationQueryController {

    @Autowired
    private RunningInformationService runningInformationService;

    @RequestMapping(value = "/bulk/runningInformations", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInformation> runningInformationList) {
        runningInformationService.saveRunningInformation(runningInformationList);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        runningInformationService.deleteAll();
    }

    @RequestMapping(value = "/runningInformations/healthWarningLevel/{healthWarningLevel}", method = RequestMethod.GET)
    public ResponseEntity<?> findByHealthWarningLevel(@PathVariable String healthWarningLevel,
                                                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                       @RequestParam(name = "size", required = false, defaultValue = "2") Integer size) {

        Page<RunningInformation> rawResult = runningInformationService.findByHealthWarningLevel(healthWarningLevel, new PageRequest(page, size));

        return new ResponseEntity<List<JSONObject>>(convertToJson(rawResult), HttpStatus.OK);
    }

    @RequestMapping(value = "/runningInformations", method = RequestMethod.GET)
    public ResponseEntity<?> findAllRunningInformations(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "2") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "healthWarningLevel") String sort) {

        Page<RunningInformation> rawResult = runningInformationService.findAllRunningInformations(new PageRequest(page, size, Sort.Direction.DESC, sort));

        return new ResponseEntity<List<JSONObject>>(convertToJson(rawResult), HttpStatus.OK);
    }

    @RequestMapping(value = "/runningInformations/runningId/{runningId}", method = RequestMethod.DELETE)
    public void deleteByRunningId(@PathVariable String runningId) {
        runningInformationService.deleteByRunningId(runningId);
    }

    @RequestMapping(value = "/runningInformations/runningId/{runningId}", method = RequestMethod.GET)
    public JSONObject findByRunningId(@PathVariable String runningId) {
        return convertToJson(runningInformationService.findByRunningId(runningId));
    }

    private List<JSONObject> convertToJson(Page<RunningInformation> rawResult) {

        List<RunningInformation> content = rawResult.getContent();

        // Transform RunningInformation to customized JSON objects
        // JSON?
        List<JSONObject> results = new ArrayList<JSONObject>();
        for (RunningInformation item : content) {
            results.add(convertToJson(item));
        }

        return results;

    }

    private JSONObject convertToJson(RunningInformation runningInformation) {
        JSONObject jsonObject = null;

        if (runningInformation != null) {
            jsonObject = new JSONObject();
            jsonObject.put("runningId", runningInformation.getRunningId());
            jsonObject.put("totalRunningTime", runningInformation.getTotalRunningTime());
            jsonObject.put("heartRate", runningInformation.getHeartRate());
            jsonObject.put("userId", runningInformation.getId());
            jsonObject.put("userName", runningInformation.getUserInfo().getUsername());
            jsonObject.put("userAddress", runningInformation.getUserInfo().getAddress());
            jsonObject.put("healthWarningLevel", runningInformation.getHealthWarningLevel());
        }

        return jsonObject;
    }
}