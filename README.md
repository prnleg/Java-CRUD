# Java-CRUD
O código foi adaptado do meu projeto anterior referente a um CRUD + MySQL.
Algumas das lib's presentes são do Servlet e do MySQL, eu os usei para testar o CRUD como todo e não somente a API.

## As libs que utilizei:
```java
// Utilizei .ArrayList e .List para criar uma lista no GET_All e o .Iterator para integrar os Objetos do .json
import java.util.*;

// Utilizei para dar "parser" em Objetos e poder criar os Objetos para armazena-los no .json
import org.json.simple.*;

// Utilizei para que pudesse usar o try-catch usado no Read/Write do .json
import java.io.*;
```

Estão presente também arquivos do Servlet e .jsp onde acontece:
- filtragem por "@email.com" (feito no front pelo jsp em html, user-form.jsp);
- datas em DD/MM/AAAA (feito no front pelo jsp em html, user-form.jsp);
- Nome e Ultimo nome (se for inserido mais de 1 nome);
- Cargo (tipo "form" pelo jsp em html, user-form.jsp).
 
Os principais arquivos são:
- User.java - Possui a classe e seus métodos;
- UserDAO.java - Possui os métodos que interligam a Classe e para o uso dos PUT, GET (Esta poluido com o MySQL, mas os métodos estão explicados);
- // UserServlet.java - conexão do UserDAO com o JSP;
- // user-for.jsp - Página com o formulário para o cadastro do Usuário;
- // user-list.jsp - Página com a chamada de todo o banco, contendo id, nome, data de nascimento, e-mail e cargo;
