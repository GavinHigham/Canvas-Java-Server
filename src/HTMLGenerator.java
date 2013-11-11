import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class HTMLGenerator {
	//The HTML is represented internally by a string.
	String stringHTML;
	//Loads the HTML file "example1.html from the same directory as this class."
	HTMLGenerator() {
		this("example1.html");
	}
	//Loads the HTML file from a particular location.
	HTMLGenerator(String URL) {
		StringBuilder HTMLBuilder = new StringBuilder();
		try {
		    BufferedReader in = new BufferedReader(new FileReader(URL));
		    String str;
		    while ((str = in.readLine()) != null) {
		        HTMLBuilder.append(str);
		    }
		    in.close();
		} catch (IOException e) {
		}
		stringHTML = HTMLBuilder.toString();
	}
	public void setClassName(String className) {
		stringHTML.replace("ClassName.class", className);
	}
	public void setJarName(String jarName) {
		stringHTML.replace("JarName.jar", jarName);
	}
	//Sets the parameters in HTML in JSON format, like so: { key1: 'value1', key2: 'value2', ...}
	public void setParameters(Map<String, String> parameters) {
		StringBuilder parametersBuilder = new StringBuilder();
		boolean first = true;
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    if (!first) parametersBuilder.append(", "); //If there's only one parameter, no comma.
		    parametersBuilder.append(key);
		    parametersBuilder.append(": '");
		    parametersBuilder.append(value);
		    parametersBuilder.append("'");
		    first = false; //After the key:value, every subsequent one starts with a comma and space.
		}
		stringHTML.replace("message: 'Hello, World! Again!'", parametersBuilder.toString());
	}
	public void setDimensions(int width, int height) {
		stringHTML.replace("width:300, height:300", "width:" + width + ", height:" + height);
	}
	public String toString() {
		return stringHTML;
	}
}
