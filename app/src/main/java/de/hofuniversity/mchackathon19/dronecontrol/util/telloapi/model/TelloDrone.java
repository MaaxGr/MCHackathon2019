package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.model;

public interface TelloDrone {

  Integer getBattery();

  void setBattery(Integer battery);

  Integer getSpeed();

  void setSpeed(Integer speed);

  String getTime();

  void setTime(String time);

  TelloConnection getTelloConnection();

  void setTelloConnection(TelloConnection telloConnection);

  TelloMode getTelloMode();

  void setTelloMode(TelloMode telloMode);
}
