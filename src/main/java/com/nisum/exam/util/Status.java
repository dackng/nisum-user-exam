package com.nisum.exam.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Status {
   ACTIVE(true), INACTIVE(false);
   private Boolean value;
   
}
