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

import org.wingsource.plugin.OperandTypeResolverService;
import org.wingsource.plugin.util.ClasspathSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author samikc
 *
 */
public class GridOperandTypeResolver implements OperandTypeResolverService {
	private static final Logger logger = Logger.getLogger(GridOperandTypeResolver.class.getName());
	private static Map <String, String> TYPE_MAP = new HashMap<String, String>();
	static {
		URL[] urls = ClasspathSearch.instance().search(GridOperandTypeResolver.class, "APP-INF", "plugin.repository");
		
		for(URL url: urls) {
			try {
				InputStream in = url.openStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String line = br.readLine();
				int l = 0;
				while (line != null) {
				  String[] tokens = line.split(",");

				  if(tokens.length >= 3) {
					  TYPE_MAP.put(tokens[0], tokens[1]);
				  }
				  else {
					  logger.log(Level.SEVERE, "Incorrect number of tokens found in plugin.repository at line# " + l);
			      }
					  
				  line = br.readLine();
				  l++;
				}
				
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		logger.finest(TYPE_MAP.toString());
	}	

	/* (non-Javadoc)
	 * @see org.wingsource.plugin.TypeResolverService#resolve(java.lang.String)
	 */
	public String resolve(String symbol) {
			String type = TYPE_MAP.get(symbol);
			System.out.println("GridOperandTypeResolver::resolve("+symbol+") returned type=" + type);
			return type;
	}

}
