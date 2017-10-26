<%@ page contentType="text/html;charset=GB2312" %> 
<HTML> 
<BODY BGCOLOR=blue> 
<FONT Size=3> 
<P>这是一个简单的JSP页面 
     <% int i, sum=0; 
         for(i=1;i<=100;i++) 
          { sum=sum+i; 
          } 
      %> 
<P>  1 到100的连续和是： 
<BR> 
   <%=sum %> 
</FONT> 
</BODY> 
<HTML>
