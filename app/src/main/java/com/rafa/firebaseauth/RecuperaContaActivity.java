package com.rafa.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.rafa.firebaseauth.databinding.ActivityRecuperaContaBinding;

public class RecuperaContaActivity extends AppCompatActivity {
    private ActivityRecuperaContaBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperaContaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        binding.recuperaBtn.setOnClickListener(view -> validaDados());

    }

    private void validaDados() {
        String email = binding.editEmail.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Informe o seu e-email", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.progressBar.setVisibility(View.VISIBLE);

        recuperaConta(email);
    }

    private void recuperaConta(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Verifique o seu email", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ocorreu um erro.", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
            }
            binding.progressBar.setVisibility(View.GONE);
        });
    }

}