package se.jobsesprit.services;

import se.jobsesprit.entities.UserEtudiant;
import se.jobsesprit.interfaces.InterfaceCRUD;
import se.jobsesprit.utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javax.management.relation.Role;

public class UserEtudiantCRUD implements InterfaceCRUD<UserEtudiant> {

@Override
public void ajouterEntities(UserEtudiant userEtudiant) {
    try {
        String requete = "INSERT INTO UserEtudiant(phone, role, email, motDePasse, firstName, lastName,username, image, age, rate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, userEtudiant.getPhone());
        pst.setString(2, userEtudiant.getRole());
        pst.setString(3, userEtudiant.getEmail());
        pst.setString(4, userEtudiant.getMotDePasse());
        pst.setString(5, userEtudiant.getFirstName());
        pst.setString(6, userEtudiant.getLastName());
        pst.setString(7, userEtudiant.getUsername());
        pst.setString(8, userEtudiant.getImage());
        pst.setInt(9, userEtudiant.getAge());
        pst.setDouble(10, userEtudiant.getRate());

        pst.executeUpdate();

        // Récupérez l'ID généré automatiquement
        ResultSet generatedKeys = pst.getGeneratedKeys();
        if (generatedKeys.next()) {
            int id = generatedKeys.getInt(1);
            userEtudiant.setId(id); // Mettez à jour l'objet avec l'ID généré
        }

        System.out.println("Utilisateur étudiant ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    @Override
    public List<UserEtudiant> listeDesEntities() {
        List<UserEtudiant> myList = new ArrayList<>();
        try {
            String requete = "SELECT id, phone, role, email, motDePasse, firstName, lastName, image, age, rate FROM UserEtudiant";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                UserEtudiant userEtudiant = new UserEtudiant();
                userEtudiant.setId(rs.getInt("id"));
                userEtudiant.setPhone(rs.getInt("phone"));
                userEtudiant.setRole(rs.getString("role"));
                userEtudiant.setEmail(rs.getString("email"));
                userEtudiant.setMotDePasse(rs.getString("motDePasse"));
                userEtudiant.setFirstName(rs.getString("firstName"));
                userEtudiant.setLastName(rs.getString("lastName"));
                userEtudiant.setImage(rs.getString("image"));
                userEtudiant.setAge(rs.getInt("age"));
                userEtudiant.setRate(rs.getDouble("rate"));

                myList.add(userEtudiant);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void modifierEntities(UserEtudiant userEtudiant) {
        try {
            String requete = "UPDATE UserEtudiant SET phone = ?, email = ?, motDePasse = ?, firstName = ?, lastName = ?, image = ?, age = ?, rate = ? WHERE email = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, userEtudiant.getPhone());
            pst.setString(2, userEtudiant.getEmail());
            pst.setString(3, userEtudiant.getMotDePasse());
            pst.setString(4, userEtudiant.getFirstName());
            pst.setString(5, userEtudiant.getLastName());
            pst.setString(6, userEtudiant.getImage());
            pst.setInt(7, userEtudiant.getAge());
            pst.setDouble(8, userEtudiant.getRate());
            pst.setString(9, userEtudiant.getEmail());

            pst.executeUpdate();
            System.out.println("Utilisateur étudiant modifié avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerEntities(UserEtudiant userEtudiant) {
        try {
            String requete = "DELETE FROM UserEtudiant WHERE email = ?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, userEtudiant.getEmail());

            pst.executeUpdate();
            System.out.println("Utilisateur étudiant supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public UserEtudiant authenticate(String mail, String password) throws SQLException{
        UserEtudiant u = null;
       

            String sql = "SELECT * FROM UserEtudiant WHERE `email` = ? AND `mot_De_Passe` = ?";
            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(sql);
            ps.setString(1, mail);
            ps.setString(2, password);
        
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u=new UserEtudiant();


                u.setId(rs.getInt("id"));
                u.setFirstName(rs.getString("last_name"));
                u.setLastName(rs.getString("first_name"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getInt("phone"));
                u.setUsername(rs.getString("username"));
                u.setMotDePasse(rs.getString("mot_De_Passe"));
                u.setImage(rs.getString("image"));
                u.setAge(rs.getInt("age"));
               // u.setRate(rs.getInt("rate"));
                u.setRole(rs.getString("role"));
                
            }

        return u;
    }
    
    public UserEtudiant findByMail(String mail) throws SQLException{
        UserEtudiant u =new UserEtudiant();
        String req="SELECT * FROM `useretudiant` WHERE `email`=?";
        PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement((req));
        st.setString(1,mail);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            u.setId(rs.getInt("id"));
                u.setFirstName(rs.getString("last_name"));
                u.setLastName(rs.getString("first_name"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getInt("phone"));
                u.setUsername(rs.getString("username"));
                u.setMotDePasse(rs.getString("mot_De_Passe"));
                u.setImage(rs.getString("image"));
                u.setAge(rs.getInt("age"));
             //   u.setRate(rs.getInt("rate"));
                u.setRole(rs.getString("role"));
        }

        return u;

    }
    
    
}
