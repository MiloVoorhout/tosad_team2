package Generate.BusinessLayer.RuleTypes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EOTH extends RuleTypesFacade {

    public static String triggerCodeEntityOtherRuleMYSQL(HashMap values) {
        String litVal = null;

        Iterator iterator = values.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry value = (Map.Entry) iterator.next();
            litVal = (String) value.getValue();
        }

        return String.format("%s", litVal);
    }
}
