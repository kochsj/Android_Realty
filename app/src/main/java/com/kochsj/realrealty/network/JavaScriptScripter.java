package com.kochsj.realrealty.network;

//import org.mozilla.javascript.Context;
//import org.mozilla.javascript.Scriptable;
//import android.util.Log;


import android.util.Log;

import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptScripter {

    /**
     * Process the passed-in JavaScript script that should include an assignment
     * to a variable with the name prescribed by the provided nameOfOutput and
     * may include parameters prescribed by inputParameters.
     *
     * @param javaScriptCodeToProcess The String containing JavaScript code to
     *    be evaluated.  This String is not checked for any type of validity and
     *    might possibly lead to the throwing of a ScriptException, which would
     *    be logged.
     * @param nameOfOutput The name of the output variable associated with the
     *    provided JavaScript script.
     * @param inputParameters Optional map of parameter names to parameter values
     *    that might be employed in the provided JavaScript script.  This map
     *    may be null if no input parameters are expected in the script.
     */
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    public static Object processArbitraryJavaScript(
            final String javaScriptCodeToProcess,
            final String nameOfOutput,
            final Map<String, Object> inputParameters)
    {
        final ScriptEngineManager manager = new ScriptEngineManager();
        Object result = null;
        final ScriptEngine engine = manager.getEngineByName("js");

        try
        {
            if (inputParameters != null)
            {
                for (final Map.Entry<String,Object> parameter :
                        inputParameters.entrySet())
                {
                    engine.put(parameter.getKey(), parameter.getValue());
                }
            }
            engine.eval(javaScriptCodeToProcess);
            result = engine.get(nameOfOutput);
        }
        catch (ScriptException scriptException)
        {
            Log.d("JAVASCRIPTER",
                "ScriptException encountered trying to write arbitrary JavaScript '"
                    + javaScriptCodeToProcess + "': "
                    + scriptException.toString()
            );
        }
        return result;
    }
}
