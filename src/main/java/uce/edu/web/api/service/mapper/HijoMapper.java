package uce.edu.web.api.service.mapper;

import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.to.HijoTo;

public class HijoMapper {

  public static HijoTo toTo(Hijo hijo) {
    HijoTo hijoTo = new HijoTo();
    hijoTo.setId(hijo.getId());
    hijoTo.setNombre(hijo.getNombre());
    hijoTo.setApellido(hijo.getApellido());

    if (hijo.getEstudiante() != null) {
      hijoTo.setEstudianteId(hijo.getEstudiante().getId());
    }
    return hijoTo;
  }

  public static Hijo toEntity(HijoTo hijoTo) {
    Hijo hijo = new Hijo();
    hijo.setId(hijoTo.getId());
    hijo.setNombre(hijoTo.getNombre());
    hijo.setApellido(hijoTo.getApellido());
    return hijo;
  }
}
