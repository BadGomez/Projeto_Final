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

    public AdaptadorPacientes(Context context){this.context = context;}

    public void setCursor(Cursor cursor){
        if (cursor != this.cursor){
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolderPaciente onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemPaciente = LayoutInflater.from(context).inflate(R.layout.item_Paciente, parent, false);
        return new ViewHolderPaciente(itemPaciente);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPaciente holder, int position){
        cursor.moveToPosition(position);
        PacienteModel pacienteModel = Converte.cursorToPaciente(cursor);
        holder.setPacienteModel(pacienteModel);
    }

    @Override
    public int getItemCount(){
        if(cursor == null){
            return 0;
        }
        return cursor.getCount();
    }

    public PacienteModel getPacienteSelecionado(){
        if(viewHolderPacienteSelecionado == null) return null;
        return viewHolderPacienteSelecionado.pacienteModel;
    }

    private AdaptadorPacientes.viewHolderPaciente viewHolderPacienteSelecionado = null;

    public class ViewHolderPaciente extends RecyclerView.ViewHolder implements View.OnClickListener{

        //todo: layout realizado (textView)

        public ViewHolderPaciente(@NonNull View itemView) {
            super(itemView);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

        }
    }
}
