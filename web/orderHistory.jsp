<%-- 
    Document   : orderHistory
    Created on : May 6, 2020, 9:22:05 PM
    Author     : TiTi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order History Page</title>
        <style>
            th, td {
                padding: 0 5px;
            }
        </style>
    </head>
    <body>
        <s:if test="#session.USER != null">
            <h3 style="color: orangered">Welcome, <s:property value="#session.USER.fullname"/>!</h3>
            <s:a href="logout">Logout</s:a>
        </s:if>
        <s:else>
            <s:a href="login.jsp" >Login</s:a>
        </s:else>

        <s:a href="searchProduct">
            <h4>Home</h4>
        </s:a>

        <h1>Order History</h1>

        <s:form action="orderHistory" method="POST">
            <s:textfield name="productName" label="Product name"/>
            <s:textfield name="fromDateStr" label="From date" type="date"/>
            <s:textfield name="toDateStr" label="To date" type="date"/>
            <s:submit value="Search"/>
        </s:form>

        <s:if test="orders == null || orders.isEmpty()">
            <h3>Your shopping history is empty.</h3>
        </s:if>
        <s:else>
            <p><em>There are <strong>${orders.size()} orders</strong> in your history.</em></p>
            <s:iterator value="orders" var="order">
                <strong>Order #<s:property value="id"/></strong>
                <br/>
                Created date: <s:date name="createdDate" format="dd/MM/yyyy hh:mm:ss"/>

                <s:set var="totalPrice" value="%{0}"/>
                <s:set var="details" value="tblOrderDetailsCollection"/>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Product name</th>
                            <th>Image</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="#details" status="counter">
                            <s:form action="updateCart" method="POST" theme="simple">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${productId.name}
                                    </td>
                                    <td>
                                        <img src="${productId.image}" height="90px"/>
                                    </td>
                                    <td style="text-align: right">
                                        <s:number name="price" currency="vnd"/> vnd
                                    </td>
                                    <td style="text-align: center">
                                        ${quantity}
                                    </td>
                                    <td>
                                        <s:set var="total" value="%{price * quantity}"/>
                                        <s:number name="#total" currency="vnd"/> vnd
                                    </td>
                                </tr>

                                <s:set var="totalPrice" value="%{#totalPrice + price * quantity}"/>
                            </s:form>
                        </s:iterator>
                    </tbody>
                </table>
                <p><em>Total price: <strong><s:number name="#totalPrice" currency="vnd"/> vnd</strong></em></p>

                <br/>
            </s:iterator>
        </s:else>
    </body>
</html>
