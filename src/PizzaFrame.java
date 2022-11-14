import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;



public class PizzaFrame extends JFrame{
	Connection conn=null;
	PreparedStatement state=null;
	ResultSet result=null;
	
	// помощни променливи за проследяване на id-тата на отделните номенкалтури
	int id=-1;
	int idpz=-1;
	int idcl=-1;
	int idspr=-1;
	
	//общ таб панел
		JTabbedPane tab=new JTabbedPane();
		
		//Основен панел за поръчки
		JPanel pizzaPanel=new JPanel();
		
		
		
		//подпанели за поръчки
		JPanel pizzaPanel1=new JPanel();
		JPanel pizzaPanel2=new JPanel();
		JPanel pizzaPanel3=new JPanel();
		JPanel pizzaSearchPanel=new JPanel();
		
		//етикети
		JLabel pizzaNameL=new JLabel("Пица:");
		JLabel pizzaClientL=new JLabel("Клиент:");
		JLabel pizzaQuantityL=new JLabel("Количество:");
		JLabel pizzaPriceL=new JLabel("Цена:");
		JLabel pizzaDateL=new JLabel("Дата:");
		
		private static final long serialVersionUID = 4L;
		
		//текстови полета за въвеждане на поръчки
	
		JTextField pizzaQuantityTF=new JTextField();
		JTextField pizzaPriceTF=new JTextField();
		JTextField pizzaDateTF=new JTextField();
		
		//комбобокс за номенклатурите
		JComboBox<String> comboPz=new JComboBox<String>();
		JComboBox<String> comboCl=new JComboBox<String>();
		
		//бутони за панела поръчки
		JButton BAdd=new JButton("Добави");
		JButton BDel=new JButton("Изтрий");
		JButton BEdit=new JButton("Промени");
		
		
		JLabel pizzaSearchL=new JLabel("Цена:");
		JTextField pizzaSearchTF=new JTextField();
		
		JButton BSearch=new JButton("Търси");
		
		//таблица+скроол за визуализация
		JTable pizzaTable=new JTable();
		JScrollPane pizzaScroll=new JScrollPane(pizzaTable);
		
		//таб пица
		//етикет
		JLabel pNameL=new JLabel("Пица:");
		JLabel pWeightL=new JLabel("Грамаж:");
		JLabel pDescL=new JLabel("Описание:");
		//текстово поле
		JTextField pNameTF=new JTextField();
		JTextField pWeightTF=new JTextField();
		JTextField pDescTF=new JTextField();
		
		//основен панел за пица
		JPanel pPanel=new JPanel();
		//подпанели
		JPanel pPanel1=new JPanel();
		JPanel pPanel2=new JPanel();
		JPanel pPanel3=new JPanel();
		JPanel PSearchPanel=new JPanel();

		//бутони за панела пица
		JButton PAdd=new JButton("Добави");
		JButton PDel=new JButton("Изтрий");
		JButton PEdit=new JButton("Промени");
		
		JLabel pSearchL=new JLabel("Грамаж:");
		JTextField pSearchTF=new JTextField();
		
		JButton PSearch=new JButton("Търси");
		
		//таблица+скроол за пица
		JTable pTable=new JTable();
		JScrollPane pScroll=new JScrollPane(pTable);
		
		//таб клиент
		//етикет
		JLabel clNameL=new JLabel("Клиент:");
		JLabel clCityL=new JLabel("Населено място:");
		JLabel clPhoneL=new JLabel("Телефон за връзка:");
		//текстово поле за въвеждане
		JTextField clNameTF=new JTextField();
		JTextField clCityTF=new JTextField();
		JTextField clPhoneTF=new JTextField();

		//основен панел
		JPanel clPanel=new JPanel();
		//подпанели
		JPanel clPanel1=new JPanel();
		JPanel clPanel2=new JPanel();
		JPanel clPanel3=new JPanel();
		JPanel clSearchPanel=new JPanel();

		//бутони за клиент
		JButton ClAdd=new JButton("Добави");
		JButton ClDel=new JButton("Изтрий");
		JButton ClEdit=new JButton("Промени");
		
		JLabel clSearchL=new JLabel("Населено място:");
		JTextField clSearchTF=new JTextField();
		
		JButton ClSearch=new JButton("Търси");
		
		//таблица+скроол за клиент
		JTable clTable=new JTable();
		JScrollPane clScroll=new JScrollPane(clTable);

		//трябва да си добавим справка
		//таб справка: за max цена и населено място
		//етикет
		JLabel sprPizzaPriceL=new JLabel("Въведете максимална цена:");
		//текстово поле за въвеждане
		JTextField sprPizzaPriceTF=new JTextField();
		JLabel sprClCityL=new JLabel("Въведете населено място:");
		//текстово поле за въвеждане
		JTextField sprClCityTF=new JTextField();

		//основен панел
		JPanel sprPanel=new JPanel();
		//подпанели
		JPanel sprPanel1=new JPanel();
		JPanel sprPanel2=new JPanel();
		JPanel sprPanel3=new JPanel();

		//бутони за търсене
		JButton sPClSearch=new JButton("Търси");
		
		
		//таблица+скроол за за справката
		JTable sprTable=new JTable();
		JScrollPane sprScroll=new JScrollPane(sprTable);

		
		public PizzaFrame() {
			
			conn=MyDBConnection.getConnection();

			this.setSize(600, 720);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setTitle("Пица Поръчки");
			
			// tab Поръчки
			tab.add(pizzaPanel,"Поръчки");
			pizzaPanel.setLayout(new GridLayout(4,1));
			//първи подпанел
			pizzaPanel.add(pizzaPanel1);
			
		
			pizzaPanel1.add(pizzaNameL);pizzaPanel1.add(comboPz);
			pizzaNameL.setPreferredSize(new Dimension(200,25));
			comboPz.setPreferredSize(new Dimension(350,25));
			
			pizzaPanel1.add(pizzaQuantityL);pizzaPanel1.add(pizzaQuantityTF);
			pizzaQuantityL.setPreferredSize(new Dimension(200,25));
			pizzaQuantityTF.setPreferredSize(new Dimension(350,25));
			
			pizzaPanel1.add(pizzaPriceL);pizzaPanel1.add(pizzaPriceTF);
			pizzaPriceL.setPreferredSize(new Dimension(200,25));
			pizzaPriceTF.setPreferredSize(new Dimension(350,25));
			
			pizzaPanel1.add(pizzaClientL);pizzaPanel1.add(comboCl);
			pizzaClientL.setPreferredSize(new Dimension(200,25));
			comboCl.setPreferredSize(new Dimension(350,25));
			
			pizzaPanel1.add( pizzaDateL);pizzaPanel1.add( pizzaDateTF);
			pizzaDateL.setPreferredSize(new Dimension(200,25));
			pizzaDateTF.setPreferredSize(new Dimension(350,25));
			
			//втори подпанел за бутоните
			pizzaPanel.add(pizzaPanel2);
			
			
			pizzaPanel2.add(BAdd);pizzaPanel2.add(BDel);pizzaPanel2.add(BEdit);
			
			pizzaPanel.add(pizzaSearchPanel);
			pizzaSearchPanel.add(pizzaSearchL);pizzaSearchPanel.add(pizzaSearchTF);
			pizzaSearchTF.setPreferredSize(new Dimension(350,30));
			pizzaSearchPanel.add(BSearch);
			
			// БУТОНИ 
			BAdd.addActionListener(new AddPizzaDB());
			BDel.addActionListener(new DelPizzaDB());
			BEdit.addActionListener(new EditPizzaDB());
			BSearch.addActionListener(new SearchPizzaDB());
			
			pizzaPanel.add(pizzaPanel3);
			pizzaScroll.setPreferredSize(new Dimension(550, 150));
			pizzaPanel3.add(pizzaScroll);
			
			//таб пица  
			tab.add(pPanel,"Пица");
			pPanel.setLayout(new GridLayout(4,1));
			pPanel.add(pPanel1);

			
			pPanel1.add(pNameL);pPanel1.add(pNameTF);
			pNameL.setPreferredSize(new Dimension(200,30));
			pNameTF.setPreferredSize(new Dimension(350,30));
			
			pPanel1.add(pWeightL);pPanel1.add(pWeightTF);
			pWeightL.setPreferredSize(new Dimension(200,30));
			pWeightTF.setPreferredSize(new Dimension(350,30));
			
			pPanel1.add(pDescL);pPanel1.add(pDescTF);
			pDescL.setPreferredSize(new Dimension(200,30));
			pDescTF.setPreferredSize(new Dimension(350,30));
			
			

			pPanel.add(pPanel2);
			pPanel2.add(PAdd);pPanel2.add(PDel);pPanel2.add(PEdit);
			
			pPanel.add(PSearchPanel);
			PSearchPanel.add(pSearchL);PSearchPanel.add(pSearchTF);
			pSearchTF.setPreferredSize(new Dimension(350,30));
			PSearchPanel.add(PSearch);
			
			// БУТОНИ 
			PAdd.addActionListener(new AddPDB());
			PDel.addActionListener(new DelPDB());
			PEdit.addActionListener(new EditPDB());
			PSearch.addActionListener(new SearchPDB());
			
			pPanel.add(pPanel3);
			pScroll.setPreferredSize(new Dimension(550, 150));
			pPanel3.add(pScroll);
			
			//таб клиент  
			tab.add(clPanel,"Клиент");
			clPanel.setLayout(new GridLayout(4,1));
			clPanel.add(clPanel1);

			
			clPanel1.add(clNameL);clPanel1.add(clNameTF);
			clNameL.setPreferredSize(new Dimension(200,30));
			clNameTF.setPreferredSize(new Dimension(350,30));
			
			clPanel1.add(clCityL);clPanel1.add(clCityTF);
			clCityL.setPreferredSize(new Dimension(200,30));
			clCityTF.setPreferredSize(new Dimension(350,30));
			
			clPanel1.add(clPhoneL);clPanel1.add(clPhoneTF);
			clPhoneL.setPreferredSize(new Dimension(200,30));
			clPhoneTF.setPreferredSize(new Dimension(350,30));
			
			

			clPanel.add(clPanel2);
			
			clPanel2.add(ClAdd);clPanel2.add(ClDel);clPanel2.add(ClEdit);
			
			clPanel.add(clSearchPanel);
			clSearchPanel.add(clSearchL);clSearchPanel.add(clSearchTF);
			clSearchTF.setPreferredSize(new Dimension(350,30));
			clSearchPanel.add(ClSearch);
			
		
			
			
			// БУТОНИ 
			ClAdd.addActionListener(new AddClDB());
			ClDel.addActionListener(new DelClDB());
			ClEdit.addActionListener(new EditClDB());
			ClSearch.addActionListener(new SearchClDB());
			
			clPanel.add(clPanel3);
			clScroll.setPreferredSize(new Dimension(550, 200));
			clPanel3.add(clScroll);
			
			
			//таб справка  
			tab.add(sprPanel,"Справка");
			sprPanel.setLayout(new GridLayout(3,1));
			sprPanel.add(sprPanel1);

			sprPanel1.add(sprPizzaPriceL);sprPanel1.add(sprPizzaPriceTF);
			sprPizzaPriceTF.setPreferredSize(new Dimension(350,30));
			sprPizzaPriceL.setPreferredSize(new Dimension(200,30));
			sprPanel1.add(sprClCityL);sprPanel1.add(sprClCityTF);
			sprClCityTF.setPreferredSize(new Dimension(350,30));
			sprClCityL.setPreferredSize(new Dimension(200,30));
			

			sprPanel.add(sprPanel2);
			sprPanel2.add(sPClSearch);
			
			// БУТОНИ 
			sPClSearch.addActionListener(new PClSearchDB());
			
			sprPanel.add(sprPanel3);
			sprScroll.setPreferredSize(new Dimension(550, 200));
			sprPanel3.add(sprScroll);
			
			this.add(tab);
			this.setVisible(true);
			this.refreshPizzaTable(); 
			this.refreshComboPz();
			this.refreshComboCl();
			this.refreshPzTable("PIZZAS", pTable);
			this.refreshClTable("CLIENTS", clTable); 
			
			//обработка на мишката в таблиците за визуализация
			pizzaTable.addMouseListener(new MouseActionPizzaTable());
			pTable.addMouseListener(new MouseActionPTable());
			clTable.addMouseListener(new MouseActionClTable());
		
			comboPz.addActionListener(new ActionListener() {
				
				//събитието обслужва комбобокса за пица само ако е отворен първия таб
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					if(tab.getSelectedIndex()==0 && idpz>0) {
						
						String str="select * from pizzas where pizza_type='"+comboPz.getSelectedItem().toString()+"'";
						try {
							state=conn.prepareStatement(str);
							result=state.executeQuery();
							result.next();
							idpz=Integer.parseInt(result.getObject(1).toString());
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}

						System.out.println(comboPz.getSelectedItem().toString());
						System.out.println(str);
						System.out.println(idpz);
					}
				 
				}
			});
			
			comboCl.addActionListener(new ActionListener() {
				
				//събитието обслужва комбобокса за клиент само ако е отворен първия таб
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					if(tab.getSelectedIndex()==0 && idcl>0) {
						
						String str="select * from clients where name='"+comboCl.getSelectedItem().toString()+"'";
						try {
							state=conn.prepareStatement(str);
							result=state.executeQuery();
							result.next();
							idcl=Integer.parseInt(result.getObject(1).toString());
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						System.out.println(comboCl.getSelectedItem().toString());
						System.out.println(str);
						System.out.println(idcl);
					}

				}
			});
			
		}
		// актуализира изгледа с таблицата за поръчки в съответния таб
		public void refreshPizzaTable() {
		
			String str="";
			str="select PIZZA_ORDERS.ORDER_Id, QUANTITY, PIZZAS.PIZZA_TYPE,PIZZA_ORDERS.PIZZA_ID, CLIENTS.NAME,PIZZA_ORDERS.CLIENT_ID,PRICE,DATETIME from PIZZA_ORDERS,CLIENTS,PIZZAS where PIZZA_ORDERS.CLIENT_ID=CLIENTS.CLIENT_ID and PIZZA_ORDERS.PIZZA_ID=PIZZAS.PIZZA_ID";
			try {
				state=conn.prepareStatement(str);
				result=state.executeQuery();
				pizzaTable.setModel(new MyModel(result));
				
				pizzaTable.getColumnModel().getColumn(3).setMinWidth(0);
				pizzaTable.getColumnModel().getColumn(3).setMaxWidth(0);
				pizzaTable.getColumnModel().getColumn(3).setWidth(0);

				pizzaTable.getColumnModel().getColumn(5).setMinWidth(0);
				pizzaTable.getColumnModel().getColumn(5).setMaxWidth(0);
				pizzaTable.getColumnModel().getColumn(5).setWidth(0);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// актуализира изгледа с таблицата за пици в съответния таб
		public void refreshPzTable(String name, JTable table) {
	
			String str="select * from "+name;
			try {
				state=conn.prepareStatement(str);
				result=state.executeQuery();
				table.setModel(new MyModel(result));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// актуализира изгледа с таблицата за клиент в съответния таб
		public void refreshClTable(String name, JTable table) {
			
			String str="select * from "+name;
			try {
				state=conn.prepareStatement(str);
				result=state.executeQuery();
				table.setModel(new MyModel(result));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// презарежда комбо кутията от таблицата за пица
		public void refreshComboPz() {
			
			idpz=-1;
			comboPz.removeAllItems();
			
			String sql="select PIZZA_ID, PIZZA_TYPE from PIZZAS";
			
			String item="";
			
			try {
				state=conn.prepareStatement(sql);
				result=state.executeQuery();
				if(result.next()) {
					idpz=Integer.parseInt(result.getObject(1).toString());
					do{
						item=result.getObject(2).toString();
						comboPz.addItem(item);
					}while(result.next());
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		// презарежда комбо кутията от таблицата за клиенти
		public void refreshComboCl() {
			
			idcl=-1;

			comboCl.removeAllItems();
			
			String sql="select CLIENT_ID, NAME from CLIENTS";
			
			String item="";
			
			try {
				state=conn.prepareStatement(sql);
				result=state.executeQuery();
				if(result.next()) {
					idcl=Integer.parseInt(result.getObject(1).toString());
					do {
						item=result.getObject(2).toString();
						comboCl.addItem(item);
					}while(result.next());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// обслужва бутона "Добавяне" за въведен поръчка
		class AddPizzaDB implements ActionListener {
			public void actionPerformed (ActionEvent arg0) {

				if(!pizzaQuantityTF.getText().isEmpty()) {
					String sql="insert into PIZZA_ORDERS(QUANTITY,PIZZA_ID,CLIENT_ID,PRICE,DATETIME) values(?,?,?,?,CURDATE())";
					try {
						state=conn.prepareStatement(sql);
						state.setInt(1, Integer.parseInt(pizzaQuantityTF.getText()));
						state.setInt(2, idpz);
						state.setInt(3, idcl);
						state.setDouble(4, Double.parseDouble(pizzaPriceTF.getText()));
		
						state.execute();
						refreshPizzaTable();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					pizzaQuantityTF.setText("");
					pizzaPriceTF.setText("");
					pizzaDateTF.setText("");
					id=-1;
				}
			}
			
			
		}
		// обслужва бутона "Изтриване" за въведен поръчка
		class DelPizzaDB implements ActionListener {
			public void actionPerformed (ActionEvent arg0) {
			
				if (id>0) {
					String sql="delete from PIZZA_ORDERS where ORDER_Id=?";
					try {
						state=conn.prepareStatement(sql);
						state.setInt(1, id);
						state.execute();
						refreshPizzaTable();
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
							
						pizzaQuantityTF.setText("");
						pizzaPriceTF.setText("");
						pizzaDateTF.setText("");
						id=-1;							
							}
						}
					}
					
					// обслужва бутона "Промени" за въведна поръчка
		class EditPizzaDB implements ActionListener {
			public void actionPerformed (ActionEvent arg0) {
					
				if(id>0) {
				String sql="update PIZZA_ORDERS set QUANTITY=?, PIZZA_ID=?, CLIENT_ID=?, PRICE=? where ORDER_Id=?";
							
				try {
					state=conn.prepareStatement(sql);
					state.setInt(1, Integer.parseInt(pizzaQuantityTF.getText()));
					state.setInt(2, idpz);
					state.setInt(3, idcl);
					state.setDouble(4, Double.parseDouble(pizzaPriceTF.getText()));
				
					state.setInt(5, id);
					state.execute();
					refreshPizzaTable();
								
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				pizzaQuantityTF.setText("");
				pizzaPriceTF.setText("");
				pizzaDateTF.setText("");
				}
							
			}
		}
		
		class SearchPizzaDB implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String sql="select PIZZA_ORDERS.ORDER_Id, QUANTITY, PIZZAS.PIZZA_TYPE,PIZZA_ORDERS.PIZZA_ID, CLIENTS.NAME,PIZZA_ORDERS.CLIENT_ID,PRICE,DATETIME from PIZZA_ORDERS,CLIENTS,PIZZAS where PIZZA_ORDERS.CLIENT_ID=CLIENTS.CLIENT_ID and PIZZA_ORDERS.PIZZA_ID=PIZZAS.PIZZA_ID AND Price=?";
				
				try {
					state=conn.prepareStatement(sql);
					state.setDouble(1, Double.parseDouble(pizzaSearchTF.getText()));
					result=state.executeQuery();
					pizzaTable.setModel(new MyModel(result));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				pizzaSearchTF.setText("");
				
			}
			
		}
		
		// обслужва бутона "Добавяне" за пица
		class AddPDB implements ActionListener {
			public void actionPerformed (ActionEvent arg0) {
			
				if(!pNameTF.getText().isEmpty()) {
					String sql="insert into PIZZAS(PIZZA_TYPE,WEIGHT,DESC) values(?,?,?)";
					try {
						state=conn.prepareStatement(sql);
						state.setString(1, pNameTF.getText());
						state.setInt(2, Integer.parseInt(pWeightTF.getText()));
						state.setString(3, pDescTF.getText());
						state.execute();
						
						refreshPzTable("PIZZAS", pTable);
						refreshComboPz();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					pNameTF.setText("");
					pWeightTF.setText("");
					pDescTF.setText("");
				}
			}
		}	
			
		// обслужва бутона "Изтриване" за пица
		class DelPDB implements ActionListener {
			public void actionPerformed (ActionEvent arg0) {

				if (idpz>0) {
					String sql="delete from PIZZAS where PIZZA_ID=?";
					try {
						state=conn.prepareStatement(sql);
						state.setInt(1, idpz);
						state.execute();
						refreshPzTable("PIZZAS", pTable);
						refreshComboPz();
						//idpr=-1;
					
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					pNameTF.setText("");
					pWeightTF.setText("");
					pDescTF.setText("");
				}
			}
		}
		
		// обслужва бутона "Промени" за пици
		class EditPDB implements ActionListener {
			public void actionPerformed (ActionEvent arg0) {

				if(idpz>0) {
					String sql="update PIZZAS set PIZZA_TYPE=?,WEIGHT=?,DESC=?  where PIZZA_ID=?";
				
					try {
						state=conn.prepareStatement(sql);
						state.setString(1, pNameTF.getText());
						state.setInt(2, Integer.parseInt(pWeightTF.getText()));
						state.setString(3, pDescTF.getText());
						state.setInt(4, idpz);
						state.execute();
						refreshPzTable("PIZZAS", pTable);
						refreshComboPz();
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					pNameTF.setText("");
					pWeightTF.setText("");
					pDescTF.setText("");
				}
				
			}
		}
		
		class SearchPDB implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
		
				String sql="select * from PIZZAS where WEIGHT=?";
				
				try {
					state=conn.prepareStatement(sql);
					state.setInt(1, Integer.parseInt(pSearchTF.getText()));
					result=state.executeQuery();
					pTable.setModel(new MyModel(result));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pSearchTF.setText("");
				
			}
			
		}
		
		// обслужва бутона "Добавяне" за клиент
		class AddClDB implements ActionListener {
			public void actionPerformed (ActionEvent arg0) {

				if(!clNameTF.getText().isEmpty()) {
					String sql="insert into CLIENTS(NAME,CITY,PHONE) values(?,?,?)";
					try {
						state=conn.prepareStatement(sql);
						state.setString(1, clNameTF.getText());
						state.setString(2, clCityTF.getText());
						state.setString(3, clPhoneTF.getText());
						state.execute();
						refreshClTable("CLIENTS", clTable);
						refreshComboCl();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					clNameTF.setText("");
					clCityTF.setText("");
					clPhoneTF.setText("");
				}
			}
		}
		
		// обслужва бутона "Изтриване" за клиент
		class DelClDB implements ActionListener {
			public void actionPerformed (ActionEvent arg0) {

				if (idcl>0) {
					String sql="delete from CLIENTS where CLIENT_ID=?";
					try {
						state=conn.prepareStatement(sql);
						state.setInt(1, idcl);
						state.execute();
						refreshClTable("CLIENTS", clTable);
						refreshComboCl();
					
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					clNameTF.setText("");
					clCityTF.setText("");
					clPhoneTF.setText("");
				}
			}
		}
		
		// обслужва бутона "Промени" за клиент
		class EditClDB implements ActionListener {
			public void actionPerformed (ActionEvent arg0) {

				if(idcl>0) {
					String sql="update CLIENTS set NAME=?,CITY=?,PHONE=? where CLIENT_ID=?";
				
					try {
						state=conn.prepareStatement(sql);
						state.setString(1, clNameTF.getText());
						state.setString(2, clCityTF.getText());
						state.setString(3, clPhoneTF.getText());
						state.setInt(4, idcl);
						state.execute();
						refreshClTable("CLIENTS", clTable);
						refreshComboCl();
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					clNameTF.setText("");
					clCityTF.setText("");
					clPhoneTF.setText("");
				}
				
			}
		}
		
		class SearchClDB implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
			
				String sql="select * from CLIENTS where CITY=?";
				
				try {
					state=conn.prepareStatement(sql);
					state.setString(1, clSearchTF.getText());
					result=state.executeQuery();
					clTable.setModel(new MyModel(result));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clSearchTF.setText("");
				
			}
			
		}
		
		class MouseActionPizzaTable implements MouseListener{

			// обслужва кликването върху ред от таблицата с поръчки
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row=pizzaTable.getSelectedRow();
				
				// зареждане на актуалното id за налични поръчки		
				id=Integer.parseInt(pizzaTable.getValueAt(row, 0).toString());
			
				pizzaDateTF.setText(pizzaTable.getValueAt(row, 7).toString());
				
				// зареждане на актуалния текст в комбобокса за пици
				comboPz.setSelectedItem(pizzaTable.getValueAt(row,2 ).toString());
				// зареждане на актуалното id за пици
				idpz=Integer.parseInt(pizzaTable.getValueAt(row, 3).toString());
				
				pizzaQuantityTF.setText(pizzaTable.getValueAt(row, 1).toString());
				pizzaPriceTF.setText(pizzaTable.getValueAt(row, 6).toString());
				
				// зареждане на актуалния текст в комбобокса за клиенти
				comboCl.setSelectedItem(pizzaTable.getValueAt(row, 4).toString());
				// зареждане на актуалното id за клиенти
				idcl=Integer.parseInt(pizzaTable.getValueAt(row, 5).toString());
			
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		class MouseActionPTable implements MouseListener{

			// обслужва кликването върху ред от таблицата с пици
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=pTable.getSelectedRow();
				idpz=Integer.parseInt(pTable.getValueAt(row, 0).toString());
				
				pNameTF.setText(pTable.getValueAt(row, 1).toString());
				pWeightTF.setText(pTable.getValueAt(row, 2).toString());
				pDescTF.setText(pTable.getValueAt(row, 3).toString());
			
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		class MouseActionClTable implements MouseListener{

			// обслужва кликването върху ред от таблицата с клиенти
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=clTable.getSelectedRow();
				idcl=Integer.parseInt(clTable.getValueAt(row, 0).toString());
				
				clNameTF.setText(clTable.getValueAt(row, 1).toString());
				clCityTF.setText(clTable.getValueAt(row, 2).toString());
				clPhoneTF.setText(clTable.getValueAt(row, 3).toString());
			
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		//клас за обслужване на бутона търсене за справка
		class PClSearchDB implements ActionListener {
			public void actionPerformed (ActionEvent arg0) {
//				
				// if проверяват и отхвърлят празно текстово поле
				if(!(sprPizzaPriceTF.getText().isEmpty())&&!(sprClCityTF.getText().isEmpty())) {
					if(Double.parseDouble(sprPizzaPriceTF.getText())>0) {
				
						
						String str="select PO.ORDER_ID,C.NAME,C.CITY,PO.PRICE FROM PIZZA_ORDERS PO JOIN CLIENTS C ON PO.CLIENT_ID=C.CLIENT_ID GROUP BY PO.ORDER_ID,C.CLIENT_ID,C.NAME,C.CITY HAVING PO.PRICE<=? AND C.CITY=?";
						
						try {
							state=conn.prepareStatement(str);
							state.setDouble(1, Double.parseDouble(sprPizzaPriceTF.getText()));
							state.setString(2, sprClCityTF.getText());
							result=state.executeQuery();
							sprTable.setModel(new MyModel(result));
					
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sprPizzaPriceTF.setText("");
						sprClCityTF.setText("");
					}
				}
			}

		}
		
		
}
