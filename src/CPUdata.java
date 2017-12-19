import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
 
public class CPUdata
{
private String printUsage() {
OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
Object value = null;
System.out.print("n");
for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods())
{
method.setAccessible(true);
if (method.getName().startsWith("get")&& Modifier.isPublic(method.getModifiers()))
{

try
{
value =   method.invoke(operatingSystemMXBean);
}
catch (Exception e)
{
value = e;
e.printStackTrace();
}

}

}
return (String) value;
}

}
