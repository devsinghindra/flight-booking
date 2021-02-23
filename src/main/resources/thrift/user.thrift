namespace java com.devsinghindra.flight

enum TUserType{
    ADMIN=1,
    CUSTOMER=2
}
struct TUserDetails{
    1: required string profileId;
    2: required string name;
    4: required TUserType type;
    5: optional string currentToken;
}
struct TUserCreateRequest{
    1: required string name;
    2: required string password;
    3: required TUserType type;
}
exception BadRequestException{
    1: string message;
}
exception InternalServerException{
    1: string message;
}
service TUserService{
    TUserDetails signUp(1: TUserCreateRequest request) throws (1: BadRequestException e1,2: InternalServerException e2);
    TUserDetails login(1: TUserCreateRequest request) throws (1: BadRequestException e1,2: InternalServerException e2);
    TUserDetails validateToken(1: string token) throws (1: BadRequestException e1,2: InternalServerException e2);
}