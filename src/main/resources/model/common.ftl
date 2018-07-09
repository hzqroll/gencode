package ${packageName};

<#list imports as import>
    import ${import}
</#list>

/**
 * ${classDesc}
 *
 * @author ${author}
 * Date: ${date}
 */
public ${classKind} ${className} {

<#list functions as function>
    /**
     * ${function.funDesc}
     */
    ${function.funAccess} ${function.funReturn} ${function.funName}(<#list function.funArgs as args>${args.argType} ${args.argName}<#sep>, </#list>)
</#list>
}