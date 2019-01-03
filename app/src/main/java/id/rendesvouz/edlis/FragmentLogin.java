package id.rendesvouz.edlis;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentLogin extends Fragment {


    View view;
    String Username;
    String Email;
    public FragmentLogin() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_fragment,container,false);
        final LinearLayout fl_login = (LinearLayout) view.findViewById(R.id.fl_login);
        final LinearLayout fl_register = (LinearLayout) view.findViewById(R.id.fl_register);
        final LinearLayout fl_forgotPassword = (LinearLayout) view.findViewById(R.id.fl_forgotPassword);
        final LinearLayout fl_changePassword = (LinearLayout) view.findViewById(R.id.fl_changePassword);
        final TextView tvLogin = (TextView) view.findViewById(R.id.tvLogin);
        final TextView tvRegister = (TextView) view.findViewById(R.id.tvRegister);
        final TextView tvClickHere = (TextView) view.findViewById(R.id.tvClickHere);
        final TextView tvFogotPassword = (TextView) view.findViewById(R.id.tvForgotPassword);
        final ImageView img_card = (ImageView) view.findViewById(R.id.img_header_login_register);
        final ImageView img_header_free = (ImageView) view.findViewById(R.id.img_header_free);
        final TextView tvForgotPassword2 = (TextView) view.findViewById(R.id.tvForgotPassword2);
        final TextView tvChangePassword = (TextView) view.findViewById(R.id.tvChangePassword);

        tvChangePassword.setVisibility(View.GONE);
        fl_changePassword.setVisibility(View.GONE);


        final EditText etNewPassword = (EditText) view.findViewById(R.id.etPasswordNew);
        final EditText etConfirmPasswordNew = (EditText) view.findViewById(R.id.etConfirmPasswordNew);
        final Button btnChangePassword = (Button) view.findViewById(R.id.btnNewPassword);

        final EditText etHintForgotPassword = (EditText) view.findViewById(R.id.etHintForgotPassword);
        final EditText etEmailForgotPassword = (EditText) view.findViewById(R.id.etEmailForgotPassword);
        final Button btnForgotPassword = (Button) view.findViewById(R.id.btnForgotPassword);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NewPassword = etNewPassword.getText().toString();
                String ConfirmNewPassword = etConfirmPasswordNew.getText().toString();
                String EmailTemp = etEmailForgotPassword.getText().toString();

                if(NewPassword.equals(ConfirmNewPassword)){
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
                    databaseAccess.open();
                    Boolean ChangePassword = databaseAccess.UpdateUser(EmailTemp,NewPassword);
                    if(ChangePassword==true){
                        Toast.makeText(getActivity().getApplicationContext(), "Successfully Update", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(), "Failed To Update", Toast.LENGTH_SHORT).show();
                    }
                    databaseAccess.close();

                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "New Password doesnt match with confirm password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_login.setVisibility(View.GONE);
                fl_register.setVisibility(View.GONE);
                fl_forgotPassword.setVisibility(View.VISIBLE);
                tvRegister.setVisibility(View.GONE);
                tvLogin.setVisibility(View.GONE);
                img_card.setVisibility(View.GONE);
                img_header_free.setVisibility(View.VISIBLE);
                tvForgotPassword2.setVisibility(View.VISIBLE);
                tvChangePassword.setVisibility(View.GONE);
                fl_changePassword.setVisibility(View.GONE);
            }
        });

        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
                String HintForgotPassword = etHintForgotPassword.getText().toString();
                String EmailForgotPassword = etEmailForgotPassword.getText().toString();

                Boolean CheckHint = databaseAccess.CheckHint(EmailForgotPassword,HintForgotPassword);

                if(CheckHint==true){
                   fl_forgotPassword.setVisibility(View.GONE);
                   tvForgotPassword2.setVisibility(View.GONE);
                   tvChangePassword.setVisibility(View.VISIBLE);
                   fl_changePassword.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "Hint or Email Wrong", Toast.LENGTH_SHORT).show();
                }
                databaseAccess.close();
            }
        });

        tvRegister.setTextColor(Color.rgb(39,39,39));
        tvLogin.setTextColor(Color.rgb(118,118,118));

        tvForgotPassword2.setVisibility(View.GONE);
        img_header_free.setVisibility(View.GONE);
        fl_login.setVisibility(View.GONE);
        fl_forgotPassword.setVisibility(View.GONE);

        tvFogotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_login.setVisibility(View.GONE);
                fl_register.setVisibility(View.GONE);
                fl_forgotPassword.setVisibility(View.VISIBLE);
                tvRegister.setVisibility(View.GONE);
                tvLogin.setVisibility(View.GONE);
                img_card.setVisibility(View.GONE);
                img_header_free.setVisibility(View.VISIBLE);
                tvForgotPassword2.setVisibility(View.VISIBLE);
                tvChangePassword.setVisibility(View.GONE);
            }
        });

        tvForgotPassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_login.setVisibility(View.VISIBLE);
                fl_register.setVisibility(View.GONE);
                fl_forgotPassword.setVisibility(View.GONE);
                tvRegister.setTextColor(Color.rgb(118,118,118));
                tvLogin.setTextColor(Color.rgb(39,39,39));

                tvChangePassword.setVisibility(View.GONE);
                tvRegister.setVisibility(View.VISIBLE);
                tvLogin.setVisibility(View.VISIBLE);
                img_card.setVisibility(View.VISIBLE);
                img_header_free.setVisibility(View.GONE);
                tvForgotPassword2.setVisibility(View.GONE);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_login.setVisibility(View.GONE);
                fl_register.setVisibility(View.VISIBLE);
                fl_forgotPassword.setVisibility(View.GONE);
                tvRegister.setTextColor(Color.rgb(39,39,39));
                tvLogin.setTextColor(Color.rgb(118,118,118));
                img_header_free.setVisibility(View.GONE);

            }
        });
        final EditText etNameRegister,etEmailRegister, etNumberRegister, etHintRegister,etPasswordRegister,etConfirmPasswordRegister;
        Button btnRegister;

        etNameRegister = (EditText) view.findViewById(R.id.etNameRegister);
        etEmailRegister = (EditText) view.findViewById(R.id.etEmailRegister);
        etNumberRegister = (EditText) view.findViewById(R.id.etNumberRegister);
        etHintRegister = (EditText) view.findViewById(R.id.etHintRegister);
        etPasswordRegister = (EditText) view.findViewById(R.id.etPasswordRegister);
        etConfirmPasswordRegister = (EditText) view.findViewById(R.id.etConfirmPasswordRegister);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);
               btnRegister.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
                       databaseAccess.open();
                       String NameRegister = etNameRegister.getText().toString();
                       String EmailRegister = etEmailRegister.getText().toString();
                       String NumberRegister = etNumberRegister.getText().toString();
                       String HintRegister = etHintRegister.getText().toString();
                       String PasswordRegister = etPasswordRegister.getText().toString();
                       String ConfirmPasswordRegister = etConfirmPasswordRegister.getText().toString();

                       if(NameRegister.equals("")||EmailRegister.equals("")||NumberRegister.equals("")||HintRegister.equals("")||
                               PasswordRegister.equals("")||ConfirmPasswordRegister.equals("")){
                           Toast.makeText(getActivity().getApplicationContext(), "Fields Are Empty", Toast.LENGTH_SHORT).show();
                       }
                       else{
                           if(PasswordRegister.equals(ConfirmPasswordRegister)){
                               Boolean CheckEmail = databaseAccess.CheckEmail(EmailRegister);
                               if(CheckEmail==true){
                                   Boolean insert = databaseAccess.InsertUser(NameRegister,PasswordRegister,EmailRegister,NumberRegister,HintRegister);
                                   if(insert==true){
                                       Toast.makeText(getActivity().getApplicationContext(), "Register Successfully", Toast.LENGTH_LONG).show();
                                       etNameRegister.setText("");
                                       etEmailRegister.setText("");
                                       etPasswordRegister.setText("");
                                       etConfirmPasswordRegister.setText("");
                                       etHintRegister.setText("");
                                       etNumberRegister.setText("");
                                   }
                               }
                               else{
                                   Toast.makeText(getActivity().getApplicationContext(), "Email Already Exists", Toast.LENGTH_SHORT).show();
                               }
                           }
                           else{
                               Toast.makeText(getActivity().getApplicationContext(), "Confirm Password Doesnt Match With Password", Toast.LENGTH_SHORT).show();
                           }
                       }
                       databaseAccess.close();
                   }
               });

               tvClickHere.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       fl_login.setVisibility(View.VISIBLE);
                       fl_register.setVisibility(View.GONE);
                       fl_forgotPassword.setVisibility(View.GONE);
                       tvRegister.setTextColor(Color.rgb(118,118,118));
                       tvLogin.setTextColor(Color.rgb(39,39,39));
                       img_header_free.setVisibility(View.GONE);

                   }
               });

        final EditText etEmail,etPassword;
        final Button btnLogin;
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etPassword = (EditText) view.findViewById(R.id.etPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);

        Singelton Passing = Singelton.getInstance();
        Username = Passing.getPassingUsername();
        Email = Passing.getPassingEmail();

        if(Username== null){
            btnLogin.setEnabled(true);
        }
        else{
            btnLogin.setEnabled(false);
        }

        if(btnLogin.isEnabled() == false){
            btnLogin.setBackgroundResource(R.color.colorDisableOrange);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
                databaseAccess.open();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                Boolean LoginValidation = databaseAccess.LoginValidation(email,password);

                if(LoginValidation==false){
                    Toast.makeText(getActivity().getApplicationContext(), "The email or password is wrong", Toast.LENGTH_LONG).show();
                }
                else{
                    String username = databaseAccess.getUsername(email,password);
                    String email1 = databaseAccess.getEmail(email,password);
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    intent.putExtra("dataUsername", username);
                    intent.putExtra("dataEmail",email1);
                    startActivity(intent);
                    getActivity().finish();
                }
                databaseAccess.close();
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_login.setVisibility(View.VISIBLE);
                fl_register.setVisibility(View.GONE);
                fl_forgotPassword.setVisibility(View.GONE);
                tvRegister.setTextColor(Color.rgb(118,118,118));
                tvLogin.setTextColor(Color.rgb(39,39,39));
                img_header_free.setVisibility(View.GONE);

            }
        });


        return view;
    }

}
