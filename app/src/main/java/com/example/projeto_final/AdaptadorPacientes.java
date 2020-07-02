package com.example.projeto_final;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorPacientes extends RecyclerView.Adapter<AdaptadorPacientes.ViewHolderPaciente> {
    private final Context context;
    private Cursor cursor = null;

    public AdaptadorPacientes(Context context) {
        this.context = context;
    }

    public void setCursor(Cursor cursor) {
        if (cursor != this.cursor) {
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolderPaciente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemPaciente = LayoutInflater.from(context).inflate(R.layout.item_paciente, parent, false);
        return new ViewHolderPaciente(itemPaciente);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPaciente holder, int position) {
        cursor.moveToPosition(position);
        Paciente pacienteModel = Converte.cursorToPaciente(cursor);
        holder.setPaciente(pacienteModel);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    public Paciente getPacienteSelecionado() {
        if (viewHolderPacienteSelecionado == null) return null;
        return viewHolderPacienteSelecionado.paciente;
    }

    private AdaptadorPacientes.ViewHolderPaciente viewHolderPacienteSelecionado = null;

    public class ViewHolderPaciente extends RecyclerView.ViewHolder{
        private Paciente paciente = null;


        public ViewHolderPaciente(@NonNull View itemView) {
            super(itemView);

        }

        public void setPaciente(Paciente paciente) {
            this.paciente = paciente;
        }


    }
}
