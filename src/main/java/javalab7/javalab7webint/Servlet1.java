package javalab7.javalab7webint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
* Вариант 12: 7: 111:
* 111000
* 1) При перезагрузке страницы сервлета должно отображаться: 1 – значение счётчика обращений к странице
сервлета после его запуска. [+]
* 1) Организовать вывод результатов работы сервлета: 1 – данные полученные от сервлета должны быть каким-то образом
размещены в видимой таблице, в таблице допускается произвольное число столбцов
и строк [+]
* 1) Реализовать при обновлении страницы сервлета: 1 -
увеличение размера текста в таблице до заданной величины, после чего на странице
должна появляться надпись (не в таблице), информирующая о том, что дальнейшее
увеличение не возможно. [+]
* 0) Реализовать возможность сброса размера текста в таблице через параметр строки url запроса:
*  0 - до значения по умолчанию [+]
* 0) Среди параметров, передаваемых в сервлет, нужно передавать Ф.И.О студента,
выполнившего разработку сервлета, и номер его группы, которые должны
отображаться следующим образом: 0 - в заголовке web-страницы возвращаемой
сервлетом клиенту; [+]
* Взять за основную функцию, которую вычисляет сервлет, реализованную в первой
лабораторной работе. [+]
* 0)При необходимости могут быть изменены порты, по которым контейнер сервлетов
Tomcat слушает запросы. Для изменения портов нужно в среде NetBeans войти в
меню «Сервис\Серверы»: 0 — оставить порт по умолчанию, [+]
*
*
*
*
*
* */


@WebServlet(urlPatterns = {"/ServletAppl"})
public class Servlet1 extends HttpServlet {


    static String ast;
    static boolean b;
    static long counter;
    static int cycle;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    public Servlet1(){
        Servlet1.ast = "a static var c=";
        Servlet1.b = false;
        Servlet1.counter = 0;
        Servlet1.cycle = 0;
    }

    public static ArrayList<Integer> GetSortedArray(ArrayList<Integer> lst_) {
        Collections.sort(lst_);
        return lst_;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Яблонскас В.С. 4311</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>ServletAppl" + request.getServletPath() + "</h1>");
            //=============I

            String studentName = request.getParameter("name");
            String studentGroup = request.getParameter("group");
            if (studentName != null && studentGroup != null){
                out.println("<h1> Student: "+studentName+" Group: "+studentGroup+"</h1>");
            }
            String numsString = request.getParameter("numbers");
            if (numsString != null){
                String[] unsortedStringArr = numsString.split(" ");
                ArrayList<Integer> unsortedIntArr = (ArrayList<Integer>) Arrays.stream(unsortedStringArr).map(Integer::parseInt).collect(Collectors.toList());
                ArrayList<Integer> sortedArr = GetSortedArray(unsortedIntArr);
                if (!sortedArr.isEmpty()){
                    out.println("<h1> Sorted array "+sortedArr.toString()+"</h1>");

                }

            }


            if (Servlet1.b) Servlet1.b = false;
            else Servlet1.b = true;
            Servlet1.counter++;
            if(Servlet1.cycle < 5){
                Servlet1.cycle ++;
            }
            if (Servlet1.cycle == 5){
                out.println("<h3>Impossible to resize anymore</h3>");
            }


            out.println("<h3> Servlet1.ast + Servlet1.b :" + Servlet1.ast + Servlet1.b + "</h3>");
            out.println("<h3> Servlet1.counter : " + Servlet1.counter + "</h3>");
           // out.println("<h3> Servlet1.cycle :" + Servlet1.cycle + "</h3>");

            String[][] tbl = new String[3][3];
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    tbl[i][j] = Integer.toString(i+1) + "&" + Integer.toString(j+1);
                }
            }

            String prm1 = request.getParameter("prm1");
            String prm2 = request.getParameter("prm2");
            String prm3 = request.getParameter("prm3");
            String resize = request.getParameter("resize");

            if(resize != null && !resize.equals("")){
                if (resize.matches("^[+-]?\\d+$")){
                    Servlet1.cycle = Integer.parseInt(resize);
                } else if (resize.equals("default")) {
                    Servlet1.cycle = 1;
                }
                if(Servlet1.cycle > 5) Servlet1.cycle = 5;

            }

            out.println("<h" + (6-Servlet1.cycle) + "><table border>"+
                    "<tr>" + "<td>" + tbl[0][0] + "</td>" + "<td>prm1=" + prm1 + "</td>" + "<td>1.3</td>" +
                    "<tr>" + "<td>prm2=" + prm2 + "</td>" + "<td>pr2</td>" + "<td>2.3</td>" +
                    "<tr>" + "<td>prm3=" + prm3 + "</td>" + "<td>3.2</td>" + "<td>" + tbl[2][2] + "</td>" +
                    "</tr>"
                    + "</table></h" + (6-Servlet1.cycle) + ">");

            //=============II
            out.println("</body>");
            out.println("</html>");
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}