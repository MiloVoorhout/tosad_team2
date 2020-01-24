package com;

import org.json.JSONArray;
import org.json.JSONObject;
import Define.BusinessLayer.BusinessRule;
import Generate.BusinessLayer.Generator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/generate")
public class generateTrigger {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getTestService(@DefaultValue("0") @QueryParam("array") String array) {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        String[] part = array.split(",");
        int id = Integer.parseInt(part[0]);
        int compareStatus = Integer.parseInt(part[2]);
        int operatorID = Integer.parseInt(part[3]);
        int attributeID = Integer.parseInt(part[4]);
        int subAttributeID = Integer.parseInt(part[5]);
        int businessRuleTypeID = Integer.parseInt(part[6]);
        BusinessRule generateRule = new BusinessRule(id, part[1], compareStatus, operatorID, attributeID, subAttributeID, businessRuleTypeID);

        Generator generator = new Generator();
        String triggerCode = generator.generatorInformation(generateRule, "INSERT");
        obj.put("code", triggerCode);
        arr.put(obj);

        String result = arr.toString();

        return (result);
    }
}
