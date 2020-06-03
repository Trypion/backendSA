/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author RaelH
 */

/**
   * @ApplicationPath: esta anotação identifica o caminho do aplicativo que serve como 
   * o URI de base para todos os recursos. Isso deve ser usado na classe de configuração, 
   * ou seja, a subclasse de javax.ws.rs.core.Application. 
   * Vamos usar @ApplicationPath para nosso recurso de exemplo 
      --> http://localhost:8080/REST/resources
 */
@ApplicationPath("/api")
public class RestApplicationPath extends Application{
    
}
