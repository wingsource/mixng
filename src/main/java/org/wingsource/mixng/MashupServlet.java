/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.wingsource.mixng;

import org.wingsource.plugin.engine.PluginEngine;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pillvin
 *
 */
public class MashupServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			PluginEngine pe = new PluginEngine(new GridOperandTypeResolver());
			String expr = request.getParameter("expr");
			System.out.println(expr);
			String page = "(xslt (page (panel banner 12) (panel g1 6) (panel g1 6) bootstrap bootstraptheme jquery jqueryui bootstrapjs angularjs gadget1js bannerjs appjs) xform)";
			if (expr != null) {
				page = expr;
			}
			
			//TODO: Need to find out how content type can be set dynamically
			response.setContentType("text/html");
			
			pe.run(page, response.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		this.doGet(request, response);
	}
	
	public static void main(String[] args) {
		try {
			PluginEngine pe = new PluginEngine(new GridOperandTypeResolver());
			//String page = "(xslt (page (l g1 g2 33) (l g1 g2 33) (l g1 g2 33) wingskin shindigRpc shindigCookies shindigUtil shindigGadgets shindigUserPref jquery jqueryui wingsGadgets wingsDnd wings) xform)";
            String page = "(page (panel g1 g2 33) (panel g1 g2 33) (panel g1 g2 33) shindigUtil shindigGadgets jquery jqueryui)";
			pe.run(page, System.out);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
