package ABM;

    public class Ciudadano extends Usuario {

        private boolean habilitado;

        int numeroTelefono;
        String cuil;

        public Ciudadano(String cuil, int numeroTelefono) {
            super(cuil);
            this.numeroTelefono = numeroTelefono;
            tipoUsuario = "ciudadano";
            habilitado = true;
        }


        public int getNumeroTelefono() {
            return numeroTelefono;
        }

        public String getCuil() {
            return cuil;
        }
        public void bloquear() {
            habilitado = false;
        }

        public void desbloquear() {
            habilitado = true;
        }

        public boolean getHabilitado() {
            return habilitado;
        }
    }

