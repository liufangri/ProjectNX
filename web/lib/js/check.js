function nx_check(obj, len)
{
    if ($.trim(obj.val()) === '' || ($.trim(obj.val())).length > len) {
        return false;
    }
    return true;
}