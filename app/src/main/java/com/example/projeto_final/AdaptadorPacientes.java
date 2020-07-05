package com.example.projeto_final;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        Paciente paciente = Converte.cursorToPaciente(cursor);
        holder.setPaciente(paciente);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    private ViewHolderPaciente viewHolderPacienteSelecionado = null;

    public Paciente getPacienteSelecionado() {
        if (viewHolderPacienteSelecionado == null) return null;
        return viewHolderPacienteSelecionado.paciente;
    }



    public class ViewHolderPaciente extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Paciente paciente = null;

        private final TextView textViewNomePaciente;
        private final TextView textViewPaisPaciente;
        private final TextView textViewGeneroPaciente;
        private final TextView textViewDataAnivPaciente;
        private final TextView textViewPacienteCronico;
        private final TextView textViewEstadoAtual;
        private final TextView textViewDataestadaAtual;


        public ViewHolderPaciente(@NonNull View itemView) {
            super(itemView);

            textViewNomePaciente = (TextView)itemView.findViewById(R.id.textViewNomePaciente);
            textViewPaisPaciente = (TextView)itemView.findViewById(R.id.textViewPaisPaciente);
            textViewGeneroPaciente = (TextView)itemView.findViewById(R.id.textViewGeneroPaciente);
            textViewDataAnivPaciente = (TextView)itemView.findViewById(R.id.textViewDataAnivPaciente);
            textViewPacienteCronico = (TextView)itemView.findViewById(R.id.textViewPacienteCronico);
            textViewEstadoAtual = (TextView)itemView.findViewById(R.id.textViewEstadoAtual);
            textViewDataestadaAtual = (TextView)itemView.findViewById(R.id.textViewDataEstadoAtual);
            itemView.setOnClickListener(this);
        }

        public void setPaciente(Paciente paciente) {
            this.paciente = paciente;
            textViewNomePaciente.setText((paciente.getNome()));
            textViewPaisPaciente.setText(String.valueOf(paciente.getId_Pais()));
            textViewGeneroPaciente.setText(paciente.getGenero());
            textViewDataAnivPaciente.setText(paciente.getData_aniversario());
            textViewPacienteCronico.setText(paciente.getDoente_cronico());
            textViewEstadoAtual.setText(paciente.getEstado_atual());
            textViewDataestadaAtual.setText(paciente.getData_estado_atual());
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            if(viewHolderPacienteSelecionado == this){
                return;
            }

            if(viewHolderPacienteSelecionado != null){
                viewHolderPacienteSelecionado.desSeleciona();
            }

            viewHolderPacienteSelecionado = this;
            seleciona();

            DisplayPacientes displayPacientes = (DisplayPacientes) AdaptadorPacientes.this.context;

        }

        private void desSeleciona() {
            itemView.setBackgroundResource(android.R.color.white);
        }

        private void seleciona(){
            itemView.setBackgroundResource(R.color.colorAccent);
        }

    }
}
