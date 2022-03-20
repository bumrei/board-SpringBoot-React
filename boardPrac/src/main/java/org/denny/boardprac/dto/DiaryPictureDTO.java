package org.denny.boardprac.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "uuid")
public class DiaryPictureDTO {

    // EqualsAndHashCode
    //         - Equals   : 두 객체의 내용이 같은지, 동등성(equality) 을 비교하는 연산자
    //         - hashCode : 두 객체가 같은 객체인지, 동일성(identity) 을 비교하는 연산자
    // 즉 @EqualsAndHashCode(of = "uuid") 를 사용함으로써 uuid 가 동등성, 동일성으로 중복될 경우 하나만 만들어지게 해준다.
    // picture 에 같은 객체가 겹쳐서 9개 들어가는 상황을 해결 할 수 있다.

    private String uuid;
    private String fileName;
    private String savePath;
    private int idx;

    public String getLink() {
        return savePath+"/s_"+uuid+"_"+fileName;
    }

}
