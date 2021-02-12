package com.mycompany.mavenproject2;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.joda.time.format.DateTimeFormatter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Arrays;
import java.util.List;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;


/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AcceuilSceneController implements Initializable {

    DatabaseReference firebaseRootRef;

    List<String> myList = new ArrayList<>();

    ObservableList <Student> students = FXCollections.observableArrayList();
   
    @FXML
    private ImageView im1;

    @FXML
    private ImageView im2;

    @FXML
    private ImageView im3;
    
    @FXML
    private JFXButton cree;
    
    @FXML
    private AnchorPane acceuil;

    @FXML
    private AnchorPane resultanchor;
       
    @FXML
    private StackPane rootpane;
   
    @FXML
    private JFXTabPane tabpane;

    @FXML
    private JFXTextField browsetext;
    
    @FXML
    private AnchorPane midleanchor;
    
    @FXML
    private AnchorPane qranchor;

    @FXML
    private JFXButton browse;

    @FXML
    private JFXDrawer drawer;


    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXComboBox<String> com1;

    @FXML
    private JFXComboBox<String> com2;

    @FXML
    private JFXButton synch;

    @FXML
    private TableView<Student> tableView;

    @FXML private TableColumn<Student, Integer> cin;

    @FXML
    private TableColumn<Student, String> prenom;

    @FXML
    private TableColumn<Student, String> nom;

    @FXML
    private TableColumn<Student, String> etat;

    @FXML
    private TableColumn<Student, String> remarques;
    
    @FXML
    private Label bienvenue;

    @FXML
    private Label connexion;
    
    public int idm , idc, ids;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      /*intialisation des parametres*/
        initDrawer();
        
        com2.getItems().addAll("Francais","anglais","JEE","SOA","Math");
        com1.getItems().addAll("LFI1","LFI2","LFM2","PREPA","LFI3");
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        remarques.setCellValueFactory(new PropertyValueFactory<>("Remarques"));
        bienvenue.setText("Bienvenue: "+FXMLController.getPrefPrenomP());
        
            
            
              if (MdpOublierController.isReachableByPing("www.google.com")){
                connexion.setText("vous étes Connecter");
            }
              else{
                 connexion.setTextFill(Color.web("#ff2925"));

                  connexion.setText("vous étes Déconecter");
              }
        
        

        


    }


  @FXML
    void setAbesent(ActionEvent event) {
        JFXButton confirmer= new JFXButton("ok");
        Student selected= tableView.getSelectionModel().getSelectedItem();
        
        if (selected == null){
        AlertHendler.showMaterialDialog(rootpane, resultanchor,Arrays.asList(confirmer),"veuillez selectionner un etudiant \n Merci.", null);
        return;
        }
       
        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
        alerte.setTitle("supprission etudiant");
        alerte.setContentText("étes-vous sûr de Modifier cette présence? "+  selected.getNom()+"  "+selected.getPrenom()+" ?");
        Optional<ButtonType> reponse = alerte.showAndWait();
       
        if (reponse.get()==ButtonType.OK){
        
        
        Connection con=null;
        /* try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
            String sql="delete from etudiants where cin_e="+selected.getCin()+"";
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();*/
       
        selected.setEtat("Absent");
        tableView.refresh();
            
        /*} catch (SQLException ex) {
            Logger.getLogger(ConsulterEtudiantsController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }
    }

    @FXML
    void setPresent(ActionEvent event) {
             JFXButton confirmer= new JFXButton("ok");
        Student selected= tableView.getSelectionModel().getSelectedItem();
        
        if (selected == null){
        AlertHendler.showMaterialDialog(rootpane, resultanchor,Arrays.asList(confirmer),"veuillez selectionner un etudiant \n Merci.", null);
        return;
        }
       
        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
        alerte.setTitle("supprission etudiant");
        alerte.setContentText("étes-vous sûr de Modifier cette présence? "+  selected.getNom()+"  "+selected.getPrenom()+" ?");
        Optional<ButtonType> reponse = alerte.showAndWait();
       
        if (reponse.get()==ButtonType.OK){
        
        
        Connection con=null;
        /* try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
            String sql="delete from etudiants where cin_e="+selected.getCin()+"";
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();*/
       
        selected.setEtat("Present");
        tableView.refresh();
            
        /*} catch (SQLException ex) {
            Logger.getLogger(ConsulterEtudiantsController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }
    }
  
    @FXML
    void onAjouter(ActionEvent event) {

    }



   public void fillTable() throws ParseException, InterruptedException{
       ReadData data = new ReadData();
      String dayNames[] = new DateFormatSymbols().getWeekdays();
      Calendar date2 = Calendar.getInstance();
        
      System.out.println(dayNames[date2.get(Calendar.DAY_OF_WEEK)]);
        System.out.println("Connexion...");
        Date d = new Date();
        Connection con=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        ResultSet rs2=null;
        String sql="select * from seance where cin_p=? and jour=?";
        String sql3="select nomdep, nom_c from departement d, classe c, seance s where (s.id_classe=?) and (s.id_classe=c.id_classe)and (c.id_departement=d.id_departement)";
        try{
            
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
            
        pst=con.prepareStatement(sql);
        pst.setString(1, FXMLController.getPrefCinP());
        pst.setString(2,dayNames[date2.get(Calendar.DAY_OF_WEEK)] );

        rs=pst.executeQuery();
        
        
         students.clear();
         
         /*si il a trouver la seance avec jour et professeur*/
            System.out.println("Liste de seance trouver avec succés");
            while (rs.next()) { 
               /*parcour de seance*/
              if (compare(rs.getTime("heure")))
                /*si l'heure de maintenant est dans l'intervalle de l'heure de la seance + 1h30*/    
                { 
                    idc = rs.getInt("id_classe");
                    idm = rs.getInt("id_matiere");
                    ids = rs.getInt("id_seance");
                    
                    System.out.println("Heure dans intervalle");
                        
                        String clas="";    
                        String dep="";
                        
                        pst=con.prepareStatement(sql3);
                        pst.setInt(1, rs.getInt("id_classe"));
                        rs2=pst.executeQuery();
                        
                        if (rs2.next()){
                            
                        dep = rs2.getString("nomdep");
                        clas = rs2.getString("nom_c");
                                                        }
                       
                        /*extraction nom departement et nom classe de la seance*/
                        System.out.println("aprés jointure "+dep+" et "+clas);
                       
                        
                        System.out.println("ajout"+dep+" et "+clas+" dans path firebase");
                        /*ajout danse le path Firebase*/
                        
                        firebaseRootRef = FirebaseDatabase.getInstance().getReference().child("Users/"+dep+"/"+clas);
                        data.readData(firebaseRootRef,new ReadData.OnGetDataListener() 
                        {
                             /*traitement Firebase*/
                             
                            @Override
                            public void onSuccess(DataSnapshot dataSnapshot) {
                                
                                    String presence;
                                    System.out.println("taitement Firebase");
                                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                                        
                                        presence= (String)ds.child("Presences").getValue();
                                        myList.clear();
                                        myList.add(presence);
                                        
                                    }
                                    
                                    
                                    
                                    Iterator<String> it = myList.iterator();
                                    
                                  
                                    
                                    DateTime local = new DateTime();
                                    Duration comp_min = new Duration(5400000);
                                    Duration zero = new Duration(0);
                                    while (it.hasNext()) {
                                        try {
                                            StringTokenizer stk = new StringTokenizer(it.next());
                                            stk.nextToken();
                                            String token_cin = stk.nextToken();
                                            String token_prenom = stk.nextToken();
                                            String token_nom = stk.nextToken();
                                            String classe = stk.nextToken();
                                            String day = stk.nextToken();
                                            String month = stk.nextToken();
                                            String day_of_month = stk.nextToken();
                                            String time = stk.nextToken();
                                            String time_zone = stk.nextToken();
                                            String year = stk.nextToken();
                                            String student_date = day + " " + month + " " + day_of_month + " " + time + " " + year;
                                            
                                            
                                            
                                            
                                            
                                            
                                            String pattern =("EEE MMM dd hh:mm:ss yyyy");
                                            
                                            
                                            SimpleDateFormat format = new SimpleDateFormat(pattern,Locale.US);
                                            Date date1 = format.parse(student_date);
                                            DateTime dt1 = new DateTime(date1);
                                            System.out.println(local);
                                            System.out.println(dt1);
                                            
                                            Duration period = new Duration(dt1,local);
                                            
                                            if (period.isShorterThan(comp_min) && period.isLongerThan(zero)){
                                                students.add(new Student(token_cin,token_nom,token_prenom,"Present","Rien"));
                                                
                                                
                                            } else {
                                                students.add(new Student(token_cin,token_nom,token_prenom,"Absent","Rien"));
                                                
                                            }
                                        } catch (ParseException ex) {
                                            Logger.getLogger(AcceuilSceneController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        
                                        
                                    }                


            }

            @Override
            public void onStart() {
                //when starting
                  students.clear();
                  myList.clear();
                System.out.println("Starting");
            }

            @Override
            public void onFailure() {
                myList.clear();
                System.out.println("Failed");
            }





        });

        /*ajout dans la tableview du professeur*/
       System.out.println("ajout dans table view");
       
      
       
       
   
          
           
          
           
           
       
       tableView.setItems(students);
   
       
        }
            else{
                    /*si la seance n'existe pas */
            System.out.println("la seance n'existe pas");
            JFXButton confirmation= new JFXButton("Ok");
            confirmation.setOnAction((event) -> {
                students.clear();
            });
            AlertHendler.showMaterialDialog(rootpane, resultanchor, Arrays.asList(confirmation),"Operation echouer vous n'avez pas de cours en ce moment",null);
            synch.setDisable(false);
            }
        }
        
        }
         catch (SQLException ex) {
           System.err.print(ex);
        }
             
       
                    
                    }
        
         
          
       




     @FXML
    void onSoumettre(ActionEvent event) {
        try {
            String sql="insert into presence(cin_e,nom,prenom,id_matiere,id_classe,etat,id_seance) values(?,?,?,?,?,?,?)";
            PreparedStatement pst = null;
            Connection con = null;
            Student etudiant= new Student();
            List <List<String>> myList = new ArrayList<>();
            
            for(int i=0; i < tableView.getItems().size();i++ ){
                etudiant = tableView.getItems().get(i);
                
                myList.add(new ArrayList<>());
                
                myList.get(i).add(etudiant.getCin());
                myList.get(i).add(etudiant.getNom());
                myList.get(i).add(etudiant.getPrenom());
                myList.get(i).add(etudiant.getEtat());
                
            }
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qrgenerator","root","");
            for(int i=0; i < myList.size();i++ ){
                
                    
                    System.out.println(myList.get(i).get(1));

                    pst=con.prepareStatement(sql);
                    
                    pst.setString(1, myList.get(i).get(0));
                    
                    pst.setString(2, myList.get(i).get(1));
                    
                    pst.setString(3, myList.get(i).get(2));
                    
                    pst.setInt(4,idm);
                    
                    pst.setInt(5, idc);
                    
                    pst.setString(6, myList.get(i).get(3));
                    
                    pst.setInt(7, ids);
                    pst.executeUpdate();
                    
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }





    private void initDrawer() {
        /*intégration de l'animation dans le leftDrawer avec la toolbar a partir du click sur le humbergeur*/
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ToolBar.fxml"));
            VBox toolbar = loader.load();
            drawer.setSidePane(toolbar);
            
        } catch (IOException ex) {
           
        }
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            drawer.toggle();
        });
        drawer.setOnDrawerOpening((event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            drawer.toFront();
        });
        drawer.setOnDrawerClosed((event) -> {
            drawer.toBack();
            task.setRate(task.getRate() * -1);
            task.play();
        });
    }
    
      @FXML
    void onBrouwse(ActionEvent event) {
        /*Bouton de parcourir*/
        final DirectoryChooser dir = new DirectoryChooser();
        Stage stage = (Stage) qranchor.getScene().getWindow();
        File file= dir.showDialog(stage);
        if(file!=null){
           browsetext.setText(file.getAbsolutePath());
           browse.getStyleClass().set(1, "parcourir-button-remplie");
        }else{
        browse.getStyleClass().set(1, "parcourir-button");
        }


    }


    @FXML
    void onCreate(ActionEvent event) {

        /*creation de la reference*/

       Random ran = new Random();
       int reference = 1000+ran.nextInt(300);

       /*Verification et creation du qrcode a partir de la classe , la matiére , mot de passe*/

    if((!browsetext.getText().isEmpty())&&(com1.getSelectionModel().getSelectedItem()!=null)&&(com2.getSelectionModel().getSelectedItem()!=null)){

    String convert = com1.getSelectionModel().getSelectedItem()+" "+com2.getSelectionModel().getSelectedItem();

    /*passage de date dans le nom du fichier*/

    String timeStamp = new SimpleDateFormat("_HH_mm_ss").format(Calendar.getInstance().getTime());
    System.out.println(timeStamp);

    /*passage de mot de passe , cle de decalage de cryptage */

    convert +=" "+"skander";
    convert=convert.toUpperCase();
    int key=convert.length();
    String cry= encryption(convert,key);

    /*verification console*/

    System.out.println(cry);
    System.out.println(dencryption(cry,key));

    /*creation image dans les resources*/
    ByteArrayOutputStream out = QRCode.from(cry).to(ImageType.JPG).stream();
    File f1 = new File("C:\\Users\\Travaille\\Desktop\\Etude LFI3\\mavenproject2\\src\\main\\resources\\Qrimages\\"+reference+".jpg");

             try {
                FileOutputStream fos = new FileOutputStream(f1);
                fos.write(out.toByteArray());
                fos.flush();




        } catch (FileNotFoundException ex) {
            System.out.println("Error File");
        } catch (IOException ex) {
            System.out.println("Error IO");
        }
    /*creation image dans le chemin absolut*/
            String path =com1.getSelectionModel().getSelectedItem()+timeStamp;
            File f2 = new File(browsetext.getText()+"\\"+path+".jpg");

             try {
                FileOutputStream fos = new FileOutputStream(f2);
                fos.write(out.toByteArray());
                fos.flush();




        } catch (FileNotFoundException ex) {
            System.out.println("Error File");
        } catch (IOException ex) {
            System.out.println("Error IO");
        }
    /*alerte de sucées*/
    JFXButton confirmation= new JFXButton("Ok");
    AlertHendler.showMaterialDialog(rootpane, qranchor, Arrays.asList(confirmation),"Operation reussite Votre Code Reférence est :"+reference+".\n\n\nLe nom de votre fichier est : "+path+".", null);

    }/*alerte de d'erreur*/
    else {
                JFXButton erreur= new JFXButton("réessayer");
                AlertHendler.showMaterialDialog(rootpane, qranchor, Arrays.asList(erreur),"Un des paramétres n'est pas correctement remplie", null);
    }
      }

    @FXML
    void synch_method(ActionEvent event) throws ParseException, InterruptedException {

        
        fillTable();
       // synch.setDisable(true);





    }








    public static void FireInitialize() throws FileNotFoundException, IOException {

        FileInputStream serviceAccount = new FileInputStream("C:\\Users\\Travaille\\Desktop\\Etude LFI3\\mavenproject2\\src\\main\\java\\com\\mycompany\\mavenproject2\\quickattendancepfe-firebase-adminsdk-aqkmu-a73ce6628a.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://quickattendancepfe.firebaseio.com/")
        .build();

        FirebaseApp.initializeApp(options);

        System.out.println("Connexion");
        }

   /*methode de crypatge*/
   private String encryption(String en,int key) {
    String result="";
         char ch;
          int l = en.length();
            for(int i=0;i<l;i++) {
                        ch = en.charAt(i);
                        ch+=key;
                        result+=ch;

       }
         return(result);

  }
   /*methode de decryptage*/
 private String dencryption(String en,int key){
        String result="";
        int l = en.length();
         char ch;
            for(int i=0;i<l;i++) {
                        ch = en.charAt(i);
                        ch-=key;
                        result+=ch;

       }
         return(result);

 }
  private boolean compare(Time d){
                LocalDateTime date = new LocalDateTime();
                //Duration comp_min = new Duration(5400000);
               final Duration pr = new Duration(5400000);
                
                String time = d.toString();
                StringTokenizer st = new StringTokenizer(time,":");
                int hour = Integer.parseInt(st.nextToken());
                int minute =  Integer.parseInt(st.nextToken());
                int seconds =  Integer.parseInt(st.nextToken());
                DateTime seance = new DateTime (date.getYear(),date.getMonthOfYear(),date.getDayOfMonth(),hour,minute,seconds);
                LocalDateTime local = new LocalDateTime();
                DateTime local1 = local.toDateTime();
                
                Duration p = new Duration(seance,local1);
                Duration zero= new Duration(0);
                
                System.out.println("Heure seance: "+seance);
                System.out.println("Heure locale: "+local1);
                System.out.println("periode entre seance et heure locale :"+p);
                System.out.println("periode de comparaison :" + pr);
                if(p.isShorterThan(pr) && p.isLongerThan(zero)){
                    System.out.println("succés");
                        return(true);
                }
               System.out.println("echec");
                return(false);
        }

}
